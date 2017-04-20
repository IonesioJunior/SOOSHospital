package Paciente;

public interface CartaoFidelidade {
	
	void adicionarPontos(int pontos);	
	double aplicarDesconto(double preco);
	int getPontosCartao();

}
