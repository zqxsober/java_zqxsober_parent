package com.zqxsober.micro.service.user.pojo.po;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zqxsober
 * @Description: OrganizationNode 类
 * @Date: 2024-04-19 11:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationNode {

    /**
     * id
     */
    private int id;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 父级名称
     */
    private String parentName;

    /**
     * 父级名称
     */
    private Integer parentId;

    /**
     * 所有子集
     */
    private List<OrganizationNode> children;


    public OrganizationNode(int id, String orgName) {
        this.id = id;
        this.orgName = orgName;
    }

    /**
     * 递归查找符合条件的组织树
     * @param organizations 所有组织列表
     * @param parentId 父级id
     * @return 符合条件的组织树的根节点列表
     */
    public static List<OrganizationNode> findOrganizationTree(List<OrganizationNode> organizations, int parentId) {
        List<OrganizationNode> result = new ArrayList<>();
        for (OrganizationNode organization : organizations) {
            if (organization.getId() == parentId) {
                OrganizationNode node = new OrganizationNode();
                node.setId(organization.getId());
                node.setOrgName(organization.getOrgName());
                node.setParentName(organization.getParentName());
                node.setChildren(findOrganizationTree(organizations, organization.getParentId()));
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 示例函数：查找指定层级的组织树
     * @param organizations 所有组织列表
     * @param hierarchyId 指定的层级id
     * @return 符合条件的组织树的根节点列表
     */
    public static List<OrganizationNode> findOrganizationTreeByHierarchyId(List<OrganizationNode> organizations, int hierarchyId) {
        // 这里假设存在一个名为 getHierarchyId() 的方法，用于获取组织的层级id
        List<OrganizationNode> result = findOrganizationTree(organizations, 0);
        // 在结果中筛选出指定层级的组织树
        filterByHierarchyId(result, hierarchyId);
        return result;
    }

    /**
     * 过滤出符合指定层级的组织树
     * @param nodes 组织树节点列表
     * @param hierarchyId 指定的层级id
     */
    private static void filterByHierarchyId(List<OrganizationNode> nodes, int hierarchyId) {
        for (OrganizationNode node : nodes) {
            if (node.getChildren() != null) {
                List<OrganizationNode> filteredChildren = new ArrayList<>();
                for (OrganizationNode child : node.getChildren()) {
                    // 这里假设存在一个名为 getHierarchyId() 的方法，用于获取组织的层级id
                    if (child.getId() == hierarchyId) {
                        filteredChildren.add(child);
                    } else {
                        filterByHierarchyId(child.getChildren(), hierarchyId);
                    }
                }
                node.setChildren(filteredChildren);
            }
        }
    }

    public static void main(String[] args) {
        // 假设 organizations 是从数据库中查询出来的所有组织列表
        List<OrganizationNode> organizations = BeanUtil.copyToList(initializeOrgSampleData(), OrganizationNode.class);

        // 调用示例函数，查找指定层级的组织树
        List<OrganizationNode> tree = OrganizationNode.findOrganizationTreeByHierarchyId(organizations, 3);
        System.out.println(JSON.toJSONString(tree));
    }

    /**
     * 递归打印组织树
     * @param node 当前节点
     * @param depth 当前节点的深度（用于缩进）
     */
    private static void printOrganizationTree(OrganizationNode node, int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }
        System.out.println(indent.toString() + node.getOrgName());
        if (node.getChildren() != null) {
            for (OrganizationNode child : node.getChildren()) {
                printOrganizationTree(child, depth + 1);
            }
        }
    }


    private static List<SysBasicOrganization> initializeOrgSampleData() {
        List<SysBasicOrganization> data = new ArrayList<>();
        // 添加10个示例组织对象
        for (int i = 1; i <= 10; i++) {
            SysBasicOrganization organization = new SysBasicOrganization();
            organization.setId(i);
            organization.setOrgName("组织" + i);
            organization.setParentId(i > 1 ? String.valueOf(i - 1) : "0");
            organization.setOrgPath(generateOrgPath(i));
            organization.setHierarchyId(i % 5 + 1);
            organization.setInuser("admin");
            organization.setIntime(new Date());
            organization.setUpdateTime(new Date());
            organization.setUpdateUser("admin");
            organization.setIsDeleted(0);
            data.add(organization);
        }
        return data;
    }

    private static List<SysOrganizationHierarchy> initializeHierarchySampleData() {
        List<SysOrganizationHierarchy> data = new ArrayList<>();
        // 添加10个示例层级对象
        for (int i = 1; i <= 10; i++) {
            SysOrganizationHierarchy hierarchy = new SysOrganizationHierarchy();
            hierarchy.setId(i);
            hierarchy.setHierarchyName("层级" + i);
            hierarchy.setParentId(i > 1 ? i - 1 : 0);
            hierarchy.setHierarchyPath(generateHierarchyPath(i));
            hierarchy.setHierarchySort(i);
            hierarchy.setInuser("admin");
            hierarchy.setIntime(new Date());
            hierarchy.setUpdateTime(new Date());
            hierarchy.setUpdateUser("admin");
            hierarchy.setIsDeleted(0);
            data.add(hierarchy);
        }
        return data;
    }
    private static List<SysOrganizationCommunity> initializeCommunitySampleData() {
        List<SysOrganizationCommunity> data = new ArrayList<>();
        // 添加10个示例项目对象
        for (int i = 1; i <= 10; i++) {
            SysOrganizationCommunity community = new SysOrganizationCommunity();
            community.setId(i);
            community.setCommunityId("项目ID" + i);
            community.setOrgId(i);
            community.setOrgPath(generateCommunityPath(i));
            community.setInuser("admin");
            community.setIntime(new Date());
            community.setUpdateTime(new Date());
            community.setUpdateUser("admin");
            community.setIsDeleted(0);
            data.add(community);
        }
        return data;
    }

    private static String generateCommunityPath(int id) {
        StringBuilder orgPath = new StringBuilder();
        for (int i = 1; i <= id; i++) {
            orgPath.append(i * 1000);
            if (i < id) {
                orgPath.append(",");
            }
        }
        return orgPath.toString();
    }


    private static String generateHierarchyPath(int id) {
        StringBuilder hierarchyPath = new StringBuilder();
        for (int i = 1; i <= id; i++) {
            hierarchyPath.append(i);
            if (i < id) {
                hierarchyPath.append(",");
            }
        }
        return hierarchyPath.toString();
    }

    private static String generateOrgPath(int id) {
        StringBuilder orgPath = new StringBuilder();
        for (int i = 1; i <= id; i++) {
            orgPath.append(i);
            if (i < id) {
                orgPath.append(",");
            }
        }
        return orgPath.toString();
    }
}
