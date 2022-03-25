package com.yeqifu.sys.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建首页左侧菜单栏
 */
public class TreeNodeBuilder {
    public static List<TreeNode> build(List<TreeNode> treeNodes, Integer topPid) {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        for (TreeNode n1 : treeNodes) {
            //确定一级菜单栏
            if (n1.getPid()==topPid){
                nodes.add(n1);
            }
            //确定该一级菜单栏下的二级菜单
            for (TreeNode n2 : treeNodes) {
                if (n1.getId()==n2.getPid()){
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;
    }
}
