package com.adun.recursiondemo;

import com.adun.recursiondemo.entities.Dept;
import com.adun.recursiondemo.entities.DeptTree;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author ADun
 * @date 2022/10/6 15:28
 */
@SpringBootTest
public class RecursionTests {

    private List<Dept> deptList;

    private Map<String, List<Dept>> deptMap;

    private ObjectMapper objectMapper;

    {
        deptList = new ArrayList<>();
        deptList.add(new Dept("1", "0", "总公司"));
        deptList.add(new Dept("2", "1", "北京分公司"));
        deptList.add(new Dept("3", "1", "上海分公司"));
        deptList.add(new Dept("4", "1", "深圳分公司"));
        deptList.add(new Dept("5", "2", "海淀分公司"));
        deptList.add(new Dept("6", "2", "昌平分公司"));
        deptMap = deptList.stream().collect(Collectors.groupingBy(Dept::getPid));
    }


    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /*
     构建json数据

     {
    "id": "1",
    "pid": "0",
    "name": "总公司",
    "children": [
        {
            "id": "2",
            "pid": "1",
            "name": "北京分公司",
            "children": [
                {
                    "id": "5",
                    "pid": "2",
                    "name": "海淀分公司",
                    "children": null
                },
                {
                    "id": "6",
                    "pid": "2",
                    "name": "昌平分公司",
                    "children": null
                }
            ]
        },
        {
            "id": "3",
            "pid": "1",
            "name": "上海分公司",
            "children": null
        },
        {
            "id": "4",
            "pid": "1",
            "name": "深圳分公司",
            "children": null
        }
    ]
}
     */


    @Test
    public void testJson() throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(deptList);
        System.out.println(jsonString);
    }

    @Test
    public void buildDeptTree() throws JsonProcessingException {
        List<Dept> depts = deptListToTree(deptList, "0");
//        List<Dept> depts = deptListToTree1(deptMap, "0");
        DeptTree deptTree = new DeptTree();
        deptTree.setRoot(depts.get(0));
        String jsonValue = objectMapper.writeValueAsString(deptTree.getRoot());
        System.out.println(jsonValue);
    }


    @Test
    public void treeToList() throws JsonProcessingException {
        List<Dept> depts = deptListToTree(deptList, "0");
//        List<Dept> depts = deptListToTree1(deptMap, "0");
        DeptTree deptTree = new DeptTree();
        deptTree.setRoot(depts.get(0));
        //String jsonValue = objectMapper.writeValueAsString(deptTree.getRoot());
        //System.out.println(jsonValue);
        List<Dept> depts1 = deptTreeToList(deptTree.getRoot());
        System.out.println(objectMapper.writeValueAsString(depts1));

    }

    private List<Dept> deptListToTree(List<Dept> deptList, String pid) {
        List<Dept> targetList = new ArrayList<>();
        deptList.forEach(item -> {
            if (item.getPid().equals(pid)) { //说明找到了根
                targetList.add(item);
                List<Dept> childrenList = deptListToTree(deptList, item.getId());
                item.setChildren((childrenList.size() > 0) ? childrenList : null);
            }
        });
        return targetList;
    }

    private List<Dept> deptListToTree1(Map<String, List<Dept>> deptMap, String pid) {
        List<Dept> targetList = new ArrayList<>();
        if (deptMap.get(pid) != null && deptMap.size() > 0) {
            List<Dept> deptList = deptMap.get(pid);
            for (Dept item : deptList) {
                targetList.add(item);
                List<Dept> childrenList = deptListToTree1(deptMap, item.getId());
                item.setChildren(childrenList);
            }
        }
        return targetList;
    }


    private List<Dept> deptTreeToList(Dept dept) {
        List<Dept> targetList = new ArrayList<>();
        deptTreeToList(dept, targetList);
        targetList.sort(Comparator.comparing(Dept::getId));
        return targetList;
    }

    private void deptTreeToList(Dept dept, List<Dept> targetList) {
        targetList.add(dept); //根节点
        if (dept.getChildren() != null && dept.getChildren().size() > 0) {
            List<Dept> childrenDeptList = dept.getChildren();
            for (Dept item : childrenDeptList) {
                deptTreeToList(item, targetList);
            }
            dept.setChildren(null);
        }
    }

}
