package  com.example.annuaire.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.annuaire.model.Utilisateur;
import com.example.annuaire.repository.UtilisateurRepository;
import com.example.annuaire.service.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements  UtilisateurService {
    private final UtilisateurRepository repo;

    public UtilisateurServiceImpl( UtilisateurRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Utilisateur> findAll(){
        return repo.findAll();
    }
    @Override
    public Optional<Utilisateur> findById(Long id){
        return repo.findById(id);
    }
    @Override
    public Utilisateur create(Utilisateur u){
        if (repo.existsByEmail(u.getEmail())) {
            throw new IllegalArgumentException("Email existe déjà !");
        }
        return repo.save(u);
    }
}
