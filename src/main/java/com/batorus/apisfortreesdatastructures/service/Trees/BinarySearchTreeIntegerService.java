package com.batorus.apisfortreesdatastructures.service.Trees;

import com.batorus.apisfortreesdatastructures.model.BinarySearchTree.BST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinarySearchTreeIntegerService {

    @Autowired
    @Qualifier("bst")
    BST<Integer> binaryTreeModel;

    public boolean insertElements(List<Integer> elements) {

        for (Integer element : elements) {
            if (!binaryTreeModel.insert(element)) return false;
        }

        return true;
    }

    public List<Integer> getTreeNodes() {
        List<Integer> list = new ArrayList<>();
        for (Integer value : binaryTreeModel) list.add(value);
        return list;
    }


    public List<BST.TreeNode<Integer>> find(Integer id) {

        return binaryTreeModel.path(id);
    }

    public boolean delete(Integer integer) {
        return binaryTreeModel.delete(integer);
    }
}
