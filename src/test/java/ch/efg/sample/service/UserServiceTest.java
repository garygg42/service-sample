package ch.efg.sample.service;

import ch.efg.sample.api.IUser;
import ch.efg.sample.model.User;
import ch.efg.sample.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void shouldSaveAndFind() {
        userService.save(new User("1", "John Smith", "1"));
        userService.save(new User("1", "John Smith", "1"));
        List<IUser> found = userService.findById("1");
        assertTrue(reflectionEquals(found.get(0), new User("1", "John Smith", "1")));
        assertTrue(reflectionEquals(found.get(1), new User("1", "John Smith", "1")));
    }

    @Test
    public void shouldSaveAndFindAll() {
        List<IUser> testData = getTestData();
        userService.saveAll(testData);
        List<IUser> found = userService.findAll();
        assertTrue(reflectionEquals(found, getTestData()));
    }

    @Test
    public void shouldDelete() {
        List<IUser> testData = getTestData();
        userService.saveAll(testData);
        userService.delete("4");
        List<IUser> testDataDeletedFourth = getTestData().stream()
                .filter(e -> !Objects.equals(e.getId(), "4")).collect(Collectors.toList());
        List<IUser> found = userService.findAll();
        assertTrue(reflectionEquals(found, testDataDeletedFourth));
    }

    @Test
    public void shouldFindAllByGroupId() {
        List<IUser> testData = getTestData();
        userService.saveAll(testData);
        Map<String, List<IUser>> found = userService.findAllGroupByGroupId();
        assertTrue(reflectionEquals(found, getGroupedTestData()));
    }

    public List<IUser> getTestData() {
        List<IUser> list = new ArrayList<>();
        list.add(new User("1", "John Smith", "1"));
        list.add(new User("2", "Jane Smith", "1"));
        list.add(new User("3", "Jack Smith", "1"));
        list.add(new User("4", "Sam Jackson", "2"));
        list.add(new User("5", "Samantha Jackson", "2"));
        list.add(new User("6", "Peter Peterson", "3"));
        list.add(new User("7", "Kim Peterson", "3"));
        list.add(new User("8", "Tom Peterson", "3"));
        list.add(new User("9", "Robert Peterson", "3"));
        return list;
    }

    public Map<String, List<IUser>> getGroupedTestData() {
        Map<String, List<IUser>> map = new HashMap<>();
        List<IUser> testData = getTestData();
        map.put("1", testData.subList(0, 2));
        map.put("2", testData.subList(3, 4));
        map.put("3", testData.subList(5, 8));
        return map;
    }
}