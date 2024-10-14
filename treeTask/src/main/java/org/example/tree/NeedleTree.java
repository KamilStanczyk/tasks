package org.example.tree;

import org.example.tree.TreeParts.*;

import java.util.List;

public abstract class NeedleTree extends Tree {

    protected List<Needle> needles;

    public NeedleTree(Trunk trunk, List<Branch> branches, List<Root> roots, int age, double height, List<Needle> needles) {
        super(trunk, branches, roots, age, height);
        this.needles = needles;
    }
}
