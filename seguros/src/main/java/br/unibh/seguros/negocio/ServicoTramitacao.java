package br.unibh.seguros.negocio;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.EtapaProcesso;
import br.unibh.seguros.entidades.Funcionario;
import br.unibh.seguros.entidades.Proposta;
import br.unibh.seguros.entidades.TipoDecisao;
import br.unibh.seguros.entidades.Tramitacao;

@Stateless
@LocalBean
public class ServicoTramitacao implements DAO<Tramitacao, Long> {

	@Inject
	EntityManager em;
	@Inject
	private Logger log;

	@Override
	public Tramitacao insert(Tramitacao t) throws Exception {
		log.info("Persistindo " + t);
		em.persist(t);
		return t;
	}

	@Override
	public Tramitacao update(Tramitacao t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	@Override
	public void delete(Tramitacao t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Tramitacao find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Tramitacao.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tramitacao> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Tramitacao").getResultList();
	}

	@Override
	public List<Tramitacao> findByName(String name) throws Exception {
		return null;
	}
	
	public Tramitacao ultimaTramitacao(Proposta proposta) throws Exception {
		log.info("Encontrando o " + proposta);
		Object o = em.createNamedQuery("Tramitacao.ultimaTramitacao").setParameter("id",proposta ).getSingleResult();
		if (o != null) return (Tramitacao) o;
		return null;
	}
	
	
	public Tramitacao aprovar(Proposta proposta, Funcionario usuarioDecisao, String comentario) throws Exception{
		if (proposta == null) throw new Exception("Proposta vazia");
		Tramitacao ultimaTramitacao = ultimaTramitacao(proposta);
		if (ultimaTramitacao == null){
			if (usuarioDecisao.getPerfil().equals("Atendimento")){
				Tramitacao t = new Tramitacao();
				t.setProposta(proposta);
				t.setDataHora(new Date());
				t.setEtapaProcesso(EtapaProcesso.APROVADA);
				t.setUsuarioDecisao(usuarioDecisao);
				t.setTipoDecisao(TipoDecisao.MANUAL);
				t.setComentario(comentario);
				return insert(t);
			}else {
				throw new Exception ("Usuário não pode realizar a aprovação");
			
			}
		}return null;
	}

	


	public Tramitacao Reprovar(Proposta proposta, Funcionario usuarioDecisao, String Comentario) throws Exception{
		if (proposta == null) throw new Exception("Proposta vazia");
		Tramitacao ultimaTramitacao = ultimaTramitacao(proposta);
		if (ultimaTramitacao == null){
			if (usuarioDecisao.getPerfil().equals("Atendimento") && equals("Jurídico") && equals("Concessão")){
				Tramitacao t = new Tramitacao();
				t.setProposta(proposta);
				t.setDataHora(new Date());
				t.setEtapaProcesso(EtapaProcesso.REPROVADA);
				t.setUsuarioDecisao(usuarioDecisao);
				t.setTipoDecisao(TipoDecisao.MANUAL);
				t.setComentario(Comentario);
				return insert(t);
			}else {
				throw new Exception ("Usuário não pode realizar a aprovação");
			
			}
		}return null;
	}
			
		public Tramitacao Cancelar(Proposta proposta, Funcionario usuarioDecisao, String Comentario) throws Exception{
			if (proposta == null) throw new Exception("Proposta vazia");
			Tramitacao ultimaTramitacao = ultimaTramitacao(proposta);
			if (ultimaTramitacao == null){
				if (usuarioDecisao.getPerfil().equals("Atendimento")){
					Tramitacao t = new Tramitacao();
					t.setProposta(proposta);
					t.setDataHora(new Date());
					t.setEtapaProcesso(EtapaProcesso.CANCELADA);
					t.setUsuarioDecisao(usuarioDecisao);
					t.setTipoDecisao(TipoDecisao.MANUAL);
					t.setComentario(Comentario);
					return insert(t);
				}else {
					throw new Exception ("Usuário não pode realizar a aprovação");
				
				}
			}return null;
			
		}
		
		public Tramitacao Emitir(Proposta proposta, Funcionario usuarioDecisao, String Comentario) throws Exception{
			if (proposta == null) throw new Exception("Proposta vazia");
			Tramitacao ultimaTramitacao = ultimaTramitacao(proposta);
			if (ultimaTramitacao == null){
				if (usuarioDecisao.getPerfil().equals("Financeiro")){
					Tramitacao t = new Tramitacao();
					t.setProposta(proposta);
					t.setDataHora(new Date());
					t.setEtapaProcesso(EtapaProcesso.EMITIDA);
					t.setUsuarioDecisao(usuarioDecisao);
					t.setTipoDecisao(TipoDecisao.MANUAL);
					t.setComentario(Comentario);
					return insert(t);
				}else {
					throw new Exception ("Usuário não pode realizar a aprovação");
				
				}
			}return null;
			
		}
		
		public Tramitacao Cancelar2(Proposta proposta, Funcionario usuarioDecisao, String Comentario) throws Exception{
			if (proposta == null) throw new Exception("Proposta vazia");
			Tramitacao ultimaTramitacao = ultimaTramitacao(proposta);
			if (ultimaTramitacao == null){
				if (usuarioDecisao.getPerfil().equals("Atendimento")){
					Tramitacao t = new Tramitacao();
					t.setProposta(proposta);
					t.setDataHora(new Date());
					t.setEtapaProcesso(EtapaProcesso.CANCELADA);
					t.setUsuarioDecisao(usuarioDecisao);
					t.setTipoDecisao(TipoDecisao.MANUAL);
					t.setComentario(Comentario);
					return update(t);
				}else {
					throw new Exception ("Usuário não pode realizar a aprovação");
				
				}
			}return null;
			
		}
		
	}
	