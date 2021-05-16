package com.batorus.apisfortreesdatastructures.configuration;



import com.batorus.apisfortreesdatastructures.model.AVL.AVLTree;
import com.batorus.apisfortreesdatastructures.model.BinarySearchTree.BST;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    @Qualifier("bst")
    public BST<Integer> IntegerBinarySearchTree(){
        return new BST<>();
    }

    @Bean
    @Qualifier("avl")
    public AVLTree<Integer> IntegerAVLTree(){
        return new AVLTree<>();
    }
}