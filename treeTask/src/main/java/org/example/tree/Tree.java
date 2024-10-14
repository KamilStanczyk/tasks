package org.example.tree;
import lombok.Getter;
import lombok.Setter;
import org.example.tree.TreeParts.*;

import java.util.List;

@Setter
@Getter
public abstract class Tree {

    protected Trunk trunk;
    protected List<Branch> branches;
    protected List<Root> roots;
    protected int age;
    protected double Height;


    public Tree(Trunk trunk, List<Branch> branches, List<Root> roots, int age, double height) {
        this.trunk = trunk;
        this.branches = branches;
        this.roots = roots;
        this.age = age;
        Height = height;
    }

    public abstract void grow();
    public abstract void shedding();
    public abstract void bloom();
    public abstract void showInfo();

}
