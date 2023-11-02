package de.ait.app.controllers;

import java.util.List;

public interface CrudeController<T> {
    public void create();
    public List<T> getAll();
    public void printAll();
}
