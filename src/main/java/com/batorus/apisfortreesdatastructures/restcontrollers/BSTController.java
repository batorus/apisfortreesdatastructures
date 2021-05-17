package com.batorus.apisfortreesdatastructures.restcontrollers;

import com.batorus.apisfortreesdatastructures.model.BinarySearchTree.BST;
import com.batorus.apisfortreesdatastructures.model.dto.IntegerDTO;
import com.batorus.apisfortreesdatastructures.model.dto.IntegerList;
import com.batorus.apisfortreesdatastructures.service.Trees.BinarySearchTreeIntegerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BSTController {

    @Autowired
    BinarySearchTreeIntegerService binarySearchTreeService;

    @GetMapping(path = "/nodes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> listAction() {

        return binarySearchTreeService.getTreeNodes();
    }

    //send data like this: {"integers":[5,6,9]}
    @PostMapping(path = "/nodes", consumes = {MediaType.APPLICATION_JSON_VALUE},
                                  produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createNodesAction(@RequestBody IntegerList integerDto) {

        binarySearchTreeService.insertElements(integerDto.getIntegerList());

        return new ResponseEntity<>(binarySearchTreeService.getTreeNodes(), HttpStatus.OK);
    }


    @GetMapping(path = "/nodes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BST.TreeNode<Integer>> getNodeAction(@PathVariable Integer id) {

        return binarySearchTreeService.find(id);
    }

    //{"integer":"2"}
    @DeleteMapping("/deletenode")
    public ResponseEntity<String> deleteAction(@RequestBody IntegerDTO integer) {

        if (binarySearchTreeService.delete(integer.getInteger()))
            return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);

        return new ResponseEntity<>("Element not in the tree!", HttpStatus.NOT_FOUND);
    }

}
