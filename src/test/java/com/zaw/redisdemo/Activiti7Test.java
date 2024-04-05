package com.zaw.redisdemo;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activiti7Test {


    /*
    获取ProcessEngine对象的第一种方法
     */
    @Test
    public void test1(){

        //在工作流引擎框架中， ProcessEngine 是一个非常核心的对象，我们需要首先解决这个对象的获
        //取。获取方式很多。先来看最简单的一个基于 activiti.cfg.xml 的XML文件的配置方式。
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);

    }

    @Test
    public void test2(){
        //上面的配置文件的方式中的配置文件其实是一个Spring的配置文件，但是这并不意味着Activiti只能用
        //于Spring环境。我们也可以通过编程的方式来使用配置文件，从而来构建ProcessEngineConfiguration对
        //象，具体的实现如下：
        ProcessEngine engine = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/activiti7? nullCatalogMeansCurrent=true")
                                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                                .setJdbcPassword("123456")
                                .setJdbcUsername("root")
                                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                                .buildProcessEngine();
        System.out.println(engine);
    }

    @Test
    public void test3(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("flow/test1.bpmn20.xml").name("请假流程").deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());

    }


    @Test
    public void test4(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        List<Deployment> list = repositoryService.createDeploymentQuery().list();

        for (Deployment deployment : list) {
            System.out.println(deployment.getId());
            System.out.println(deployment.getName());
        }



        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : processDefinitionList) {
            System.out.println(processDefinition.getId());
            System.out.println(processDefinition.getName());
        }

    }

    @Test
    public void test5() {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //发起流程
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //通过流程定义id来启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("test1:1:7503");

        System.out.println(processInstance.getId());
        System.out.println(processInstance.getDeploymentId());
        System.out.println(processInstance.getDescription());


    }

    /**
     * 待办查询
     */
    @Test
    public void test6(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 待办查询 执行中的任务处理通过 TaskService来实现
        TaskService taskService = engine.getTaskService();
        // Task 对象对应的其实就是 act_ru_task 这张表的记录
        List<Task> list = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        if(list != null && list.size() > 0){
            for (Task task : list) {
                System.out.println(task.getId());
                System.out.println(task.getName());
                System.out.println(task.getAssignee());
            }
        }else{
            System.out.println("当前没有待办任务");
        }
    }


    /**
     * 任务审批
     */
    @Test
    public void test7(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 做任务申请 也需要通过 TaskService 来实现
        TaskService taskService = engine.getTaskService();
        // 根据当前登录用户查询出对应的待办信息
        List<Task> list = taskService.createTaskQuery().taskAssignee("lisi").list();
        if(list != null && list.size() > 0){
            for (Task task : list) {
                // 做对应的任务审批处理
                taskService.complete(task.getId());
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        // 完成任务
        // taskService.complete("2505");
    }
}
