package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        Predicate<Node<E>> pred = el -> el.children.size() > 2;
        Optional<Node<E>> children = findByPredicate(pred);
        return children.isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> hasParent = findBy(parent);
        boolean rsl = findBy(child).isEmpty() && hasParent.isPresent();
        if (rsl) {
            hasParent.get().children.add(new Node<>(child));
        }
        return rsl;

    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> pred = el -> el.value.equals(value);
        return findByPredicate(pred);
    }
}