package com.vlad.pp_313.dao;

import com.vlad.pp_313.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.security.Principal;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager em;
    private final RoleDao roledao;

    public UserDaoImpl(RoleDao roledao) {
        this.roledao = roledao;
    }

    @Override
    public void save(User user) {
        user.setRoles(roledao.createRolesSet(user.getRolesIndex()));
        em.persist(user);
    }


    @Override
    public List<User> index() {
        return em.createQuery("select u from User u").getResultList();
    }


    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void delete(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public void update(User updatedUser) {
        updatedUser.setRoles(roledao.createRolesSet(updatedUser.getRolesIndex()));
        em.merge(updatedUser);
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.name = :name", User.class);
        return query.setParameter("name", name).getSingleResult();
    }

    @Override
    public boolean isAllowed(Long id, Principal principal) {
        User user = getUserByName(principal.getName());
        return user.getId() == id || user.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().contains("ADMIN"));
    }
}
