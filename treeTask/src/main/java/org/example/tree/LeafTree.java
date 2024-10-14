package org.example.tree;

import org.example.tree.TreeParts.*;

import java.util.List;

public abstract class LeafTree extends Tree {

    protected List<Leaf> leafs;

    public LeafTree(Trunk trunk, List<Branch> branches, List<Root> roots, int age, double height, List<Leaf> leafs) {
        super(trunk, branches, roots, age, height);
        this.leafs = leafs;
    }
}
