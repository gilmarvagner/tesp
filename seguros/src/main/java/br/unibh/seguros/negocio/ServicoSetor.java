package br.unibh.seguros.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Setor;

@Stateless
@LocalBean
public class ServicoSetor implements DAO<Setor, Long> {
	@Inject
	EntityManager em;
	@Inject
	private Logger log;

	@Override
	public Setor insert(Setor t) throws Exception {
		log.info("Persistindo " + t);
		em.persist(t);
		return t;
	}

	@Override
	public Setor update(Setor t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	@Override
	public void delete(Setor t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Setor find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Setor.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Setor> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Setor").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Setor> findByName(String name) throws Exception {
		log.info("Encontrando o " + name);
		return em.createNamedQuery("Setor.findByName").setParameter("nome", name + "%").getResultList();
	}
	
	public List<String> getPermissoes(Setor setor){
		
		ArrayList<String> lista = new ArrayList<String>();
		
		if (hierarquiaSetorPossui(setor, "Atendimento")){
			
			lista.add("Gerente de Atendimento");
			lista.add("corretor");
		}
		else if (hierarquiaSetorPossui(setor, "Concessão")){
			
			lista.add("Gerente de Concessão");
			lista.add("Analista de Concessão");
		}
		else if (hierarquiaSetorPossui(setor, "Financeiro")){
			
			lista.add("Gerente Financeiro");
			lista.add("Analista financeiro");
		}
		else if (hierarquiaSetorPossui(setor, "Tecnologia da Informação")){
			
			lista.add("Gerente de TI");
			lista.add("Administrador");
		}
		else if (hierarquiaSetorPossui(setor, "Diretoria")){
			
			lista.add("Diretoria");
		}
		lista.add("Consulta");
		return lista;
			
		}
	
	public boolean hierarquiaSetorPossui(Setor setor, String nome){
		
		try{
			if (setor != null) {
				if (setor.getNome().startsWith(nome)){
					return true;
				}
				Setor s = ss findByIDComSetorSuperior(setor.getId()).get(0);
				if (s.getSetorSuperior() != null){
					return hierarquiaSetorPossui(s.getSetorSuperior(),nome);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}