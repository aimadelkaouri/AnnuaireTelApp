package  com.example.annuaire.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.annuaire.model.Contact;
import com.example.annuaire.repository.ContactRepository;
import com.example.annuaire.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repo;

    public ContactServiceImpl( ContactRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Contact> findAll(){
        return repo.findAll();
    }
    @Override
    public Optional<Contact> findById(Long id){
        return repo.findById(id);
    }
    @Override
    public Contact create(Contact contact){
        return repo.save(contact);
    }
    @Override
    public Contact update( Long id, Contact contact){
        contact.setId(id);
        return repo.save(contact);
    }
    @Override
    public void delete( Long id){
        repo.deleteById(id);
    }

    @Override
    public List<Contact> searchByNom( String nom){
        return List.of();
    }
}
