package bean;

import static util.MessageUtil.addErrorMessage;
import static util.MessageUtil.addInfoMessage;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.PlanetaDao;
import entidade.Planeta;

@ManagedBean
public class PlanetaBean {

	private Planeta planeta = new Planeta();
	private List<Planeta> planetas;

	public void setPlanetas(List<Planeta> planetas) {
		this.planetas = planetas;
	}

	public String salvar() {
		try {
			planeta.setDataRegistro(new Date());
			PlanetaDao.salvar(planeta);
			addInfoMessage("Sucesso", "Planeta salvo com sucesso.");
			planeta = new Planeta();
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao salvar o planeta.");
		}
		
		return null;
	}
	
	public String deletar(Planeta planeta) {
		try {
			PlanetaDao.deletar(planeta);
			addInfoMessage("Sucesso", "Planeta deletado com sucesso.");
			setPlanetas(PlanetaDao.listarPlanetas());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			addErrorMessage("Erro", "Erro ao deletar planeta.");
		}
		return null;	
	}
	
	public String mostrarDetalhes(Planeta planeta) {
		try {
			addInfoMessage("Detalhes", "Nome: " + planeta.getNome() + " e Data: " + planeta.getDataRegistro());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			addErrorMessage("Erro", "Erro ao apresentar detalhes do planeta.");
		}
		return null;	
	}

	public String maiorId(){
		Integer idMaior = null;
		Planeta planetaComIdMaior = null;
		for (Planeta item : planetas) {
			if(idMaior==null) {
				idMaior = item.getId();
			}else {
				if(item.getId()>idMaior){
					idMaior=item.getId();
					planetaComIdMaior = item;
				}
			}
		}
		addInfoMessage("Maior Id", "Nome: " + planetaComIdMaior.getNome() + " e Id: " + planetaComIdMaior.getId());
		return null;
	}
	
	public Planeta getPlaneta() {
		return planeta;
	}

	public void setPlaneta(Planeta planeta) {
		this.planeta = planeta;
	}

	/*
	 * public List<Planeta> getPlanetas() { return planetas; }
	 */
	
	public List<Planeta> getPlanetas() {
		if (planetas == null) {
			planetas = PlanetaDao.listarPlanetas();
		}
		return planetas;
	}
	
}
