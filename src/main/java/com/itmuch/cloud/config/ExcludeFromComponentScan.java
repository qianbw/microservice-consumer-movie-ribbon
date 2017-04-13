package com.itmuch.cloud.config;

/**
 * 参见文档：https://springcloud.cc/spring-cloud-netflix-zhcn.html#true-ribbon-customizing-the-ribbon-client
 * The FooConfiguration has to be @Configuration but take care that it is not in a @ComponentScan
 * for the main application context, otherwise it will be shared by all the @RibbonClients.
 * If you use @ComponentScan (or @SpringBootApplication) you need to take steps to avoid it being included
 * (for instance put it in a separate, non-overlapping package, or specify the packages to scan explicitly in the @ComponentScan).
 */
public @interface ExcludeFromComponentScan {

}
