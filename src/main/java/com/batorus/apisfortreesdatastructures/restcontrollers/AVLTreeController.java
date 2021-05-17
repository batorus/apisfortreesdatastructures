package com.batorus.apisfortreesdatastructures.restcontrollers;

import com.batorus.apisfortreesdatastructures.model.AVL.AVLTree;
import com.batorus.apisfortreesdatastructures.model.dto.IntegerDTO;
import com.batorus.apisfortreesdatastructures.model.dto.IntegerList;
import com.batorus.apisfortreesdatastructures.service.Trees.AVLTreeIntegerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AVLTreeController {

    @Autowired
    AVLTreeIntegerService avlTreeService;


    @GetMapping(path = "/avltreenodes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> listAction() {

        return avlTreeService.getTreeNodes();
    }

    //send data like this: {"integers":[5,6,9]}
    @PostMapping(path = "/avltreenodes", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                         produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createNodesAction(@RequestBody IntegerList integerDto) {

        avlTreeService.insertElements(integerDto.getIntegerList());

        return new ResponseEntity<>(avlTreeService.getTreeNodes(), HttpStatus.OK);
    }


    //    //returns the path from root to node
    @GetMapping(path = "/avltreenodes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AVLTree.AVLTreeNode<Integer>> getPathToAVLNodeAction(@PathVariable Integer id) {

        return avlTreeService.find(id);
    }

    //send data like this: {"integers":"5"}
    @DeleteMapping("/avltreedeletenodes")
    public ResponseEntity<String> deleteAction(@RequestBody IntegerDTO integer) {

        if (avlTreeService.delete(integer.getInteger()))
            return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);

        return new ResponseEntity<>("Element not in the tree!", HttpStatus.NOT_FOUND);
    }

}
