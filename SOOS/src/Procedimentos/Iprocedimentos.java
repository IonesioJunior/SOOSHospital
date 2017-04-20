package Procedimentos;

import Exceptions.ProcedimentoException;
import Paciente.Paciente;
/**
 * Interface responsavel pela variacao dos tipos de procedimento
 * */
public interface Iprocedimentos {
	double realizarProcedimento(Paciente paciente) throws ProcedimentoException;
}