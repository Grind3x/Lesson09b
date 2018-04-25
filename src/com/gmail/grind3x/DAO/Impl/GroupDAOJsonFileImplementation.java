package com.gmail.grind3x.DAO.Impl;

import com.gmail.grind3x.DAO.GroupDAO;
import com.gmail.grind3x.Group;
import com.google.gson.Gson;
import java.io.*;

public class GroupDAOJsonFileImplementation implements GroupDAO {

    public GroupDAOJsonFileImplementation() {

    }

    @Override
    public void saveGroup(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Null Group!");
        }

        try (PrintWriter pw = new PrintWriter(new File(group.getName() + ".json"))) {
            Gson gson = new Gson();
            pw.println(gson.toJson(group));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Group loadGroupByName(String groupName) {
        if (groupName == null) {
            throw new IllegalArgumentException("Null Group name!");
        }

        Group newGroup = new Group(groupName);
        try(BufferedReader reader =  new BufferedReader(new FileReader(new File(groupName + ".json")))) {
            Gson gson = new Gson();
            newGroup = gson.fromJson(reader.readLine(), Group.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return newGroup;
    }
}
