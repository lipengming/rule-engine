/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.core;

import org.apache.commons.beanutils.BeanUtils;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.CompositeKnowledgeBuilder;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.conf.EventProcessingOption;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.io.impl.UrlResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.core <br>
 * <b>类名称</b>： DroolsUtil <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/3 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class DroolsUtil {
    private static Logger log = LoggerFactory.getLogger(DroolsUtil.class);

    /**
     * 创建环境（持有）
     *
     * @param resources                       资源
     * @param eventProcessingOption           option
     * @return                                KnowledgeBase
     * @exception                             RuntimeException
     */
    public static KnowledgeBase createKnowledgeBase( DroolsResource[] resources, EventProcessingOption eventProcessingOption) {

        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        for (DroolsResource resource : resources) {
            if(!resource.isComposite()) {
                //single
                builder.add(getResource(resource),resource.getType());
            } else {
                //composite
                CompositeKnowledgeBuilder compositeKnowledgeBuilder = builder.batch();
                CompositeDroolsResource compositeDroolsResource = (CompositeDroolsResource)resource;
                compositeKnowledgeBuilder.type(compositeDroolsResource.getType());

                for(Resource r : getCompositeResource(compositeDroolsResource)) {
                    compositeKnowledgeBuilder.add(r);
                }
                compositeKnowledgeBuilder.build();
            }
        }

        if (builder.hasErrors()) {
            throw new RuntimeException(builder.getErrors().toString());
        }

        KnowledgeBaseConfiguration conf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        conf.setOption(eventProcessingOption);
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(conf);
        knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());

        // Output the packages in this knowledge base.
        Collection<KnowledgePackage> packages = knowledgeBase.getKnowledgePackages();
        StringBuilder sb = new StringBuilder();
        for (KnowledgePackage p : packages) {
            sb.append("\n  Package : " + p.getName());
            for (Rule r : p.getRules()) {
                sb.append("\n    Rule: " + r.getName());
            }
        }
        log.info("Knowledge base built with packages: " + sb.toString());
        return knowledgeBase;
    }

    private static Resource getResource(DroolsResource resource) {
        switch (resource.getPathType()) {
            case CLASSPATH:
                return ResourceFactory.newClassPathResource(resource.getResource());
            case FILE:
                return ResourceFactory.newFileResource(resource.getResource());
            case URL:
                UrlResource urlResource = (UrlResource) ResourceFactory.newUrlResource(resource.getResource());
                if (resource.getUsername() != null) {
                    log.info("Setting authentication for: "+ resource.getUsername());
                    urlResource.setBasicAuthentication("enabled");
                    urlResource.setUsername(resource.getUsername());
                    urlResource.setPassword(resource.getPassword());
                }
                return urlResource;
            case READER:
                StringReader reader = null;
                try {
                    reader = new StringReader(resource.getResource());
                    return  ResourceFactory.newReaderResource(reader);
                } catch (Exception e) {
                    try{
                        reader.close();
                    }catch (Exception e1) {
                        log.info("close error!",e1);
                    }
                }
                break;
            case BYTE:
                return ResourceFactory.newByteArrayResource(resource.getResource().getBytes());
        }
        throw new IllegalArgumentException("Unable to build this resource path type.");
    }

    private static List<Resource> getCompositeResource(CompositeDroolsResource resource) {
        List<Resource> list = new ArrayList<Resource>(resource.getSources().size());
        for(String source : resource.getSources()) {
            list.add(getResource(new DroolsResource(source,resource.getPathType(), resource.getType())));
        }
        return list;
    }

    public static String objectDetails(Object o) {
        StringBuilder sb = new StringBuilder(o.getClass().getSimpleName());

        try {
            Map<String, String> objectProperties = BeanUtils.describe(o);
            for (String k : objectProperties.keySet()) {
                sb.append(", " + k + "=\"" + objectProperties.get(k) + "\"");
            }
        } catch (IllegalAccessException e) {
            return "IllegalAccessException attempting to parse object.";
        } catch (InvocationTargetException e) {
            return "InvocationTargetException attempting to parse object.";
        } catch (NoSuchMethodException e) {
            return "NoSuchMethodException attempting to parse object.";
        }

        return sb.toString();
    }
}

