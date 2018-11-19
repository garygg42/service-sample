package ch.efg.sample.service;

import ch.efg.sample.api.IUser;
import ch.efg.sample.api.IUserService;
import ch.efg.sample.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService<IUser, String> {

    @Autowired
    private IUserRepository userRepository;

    public List<IUser> findAll() {
        return userRepository.findAll();
    }

    public List<IUser> findById(String id) {
        return userRepository.findById(id);
    }

    public <S extends IUser> List<S> saveAll(Iterable<S> var1) {
        return userRepository.saveAll(var1);
    }

    public <S extends IUser> S save(S var1) {
        return (S) userRepository.save(var1);
    }

    public IUser delete(String var1) {
        return userRepository.delete(var1);
    }

    public Map<String, List<IUser>> findAllGroupByGroupId() {
        return userRepository.findAllGroupByGroupId();
    }
}
