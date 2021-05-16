package com.batorus.apisfortreesdatastructures.service.Trees;


import com.batorus.apisfortreesdatastructures.model.AVL.AVLTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AVLTreeIntegerService {

    @Autowired
    @Qualifier("avl")
    AVLTree<Integer> avlTreeModel;

    public boolean insertElements(List<Integer> elements) {

        for (Integer element : elements) {
            if (!avlTreeModel.insert(element)) return false;
        }

        return true;
    }

    public List<Integer> getTreeNodes() {
        List<Integer> list = new ArrayList<>();
        for (Integer value : avlTreeModel) list.add(value);
        return list;
    }


    public List<AVLTree.AVLTreeNode<Integer>> find(Integer id) {
        return avlTreeModel.findElement(id);
    }

    public boolean delete(Integer integer) {
        return avlTreeModel.delete(integer);
    }
}
