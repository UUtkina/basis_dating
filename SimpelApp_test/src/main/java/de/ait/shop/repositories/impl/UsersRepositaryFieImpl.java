package de.ait.shop.repositories.impl;

import de.ait.shop.models.User;
import de.ait.shop.repositories.UsersRepository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class UsersRepositaryFieImpl implements UsersRepository {
    private final String fileName;
    private Long generatedId = 1L;

    public UsersRepositaryFieImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        try(BufferedReader reader=new BufferedReader(new FileReader(fileName))){
            return reader.lines()
                    .map(line->line.split("#"))
                    .map(parsed->new User(Long.parseLong(parsed[0]),parsed[1],parsed[2] ))
                    .collect(Collectors.toList());
        }catch (IOException e){ throw new IllegalStateException("Problem reader : " + e.getMessage());}
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            user.setId(generatedId);
            writer.write(user.getId() + "#" + user.getEmail() + "#" + user.getPassword());
            writer.newLine();

        } catch (IOException e) {
            throw new IllegalStateException("Problem save: " + e.getMessage());
        }
        generatedId++;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public User findOneByEmail(String email) {
        try(BufferedReader reader=new BufferedReader(new FileReader(fileName))){
        return reader.lines()
                .map(line->line.split("#"))
                .filter(parsed->parsed[1].equals(email))
                .findFirst()
                .map(parsed->new User(Long.parseLong(parsed[0]),parsed[1],parsed[2] ))
                .orElse(null);
        }catch (IOException e){ throw new IllegalStateException("Problem reader : " + e.getMessage());}
    }
}
