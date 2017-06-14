
CASO DE USO 1

Liberar Sistema:
	Para liberação do sistema criou-se um método que recebe três parâmetros do tipo String,  verifica se o sistema já foi liberado anteriormente ou não, por meio de uma variável “boolean”, verifica se as chaves coincidem e, caso coincidam, é delegado então a classe “BancoFuncionarios” (responsável por toda manipulação e armazenamento dos objetos relacionados a funcionário) a criação da primeira conta, recebendo como parâmetros nome e data de nascimento e retornando uma String correspondente a matricula da conta gerada, o “boolean” de liberação do sistema é então alterado para o estado “true” e a matricula é retornada pelo método.

Realizar Login:
	Para a realização do login,  foi criado uma variável do tipo Funcionário na classe “Controller” que depende do método “realizaLogin” para ser instanciada,este por sua vez recebe como parâmetros duas Strings (matricula e senha) e delega a responsabilidade de validar o usuário para a classe BancoFuncionarios, que caso não seja validada lança uma exceção do tipo “ControllerException” caso não seja encontrado a matricula referente a um usuário nos dados ou a senha não corresponda a matricula. Caso seja validada , então é verificado se já existe alguém logado por meio do método “verificaLogin” que também lança uma exceção do tipo “ControllerException” caso já exista alguém logado.Caso não exista.  então é delegado novamente um método a classe “BancoFuncionarios” para associar a referencia do variável do tipo Funcionário ao objeto presente nos dados que corresponda a matricula e senha fornecidas.

Cadastro de funcionários:
	Para essa função foi criado um método que recebe como parâmetros  nome, cargo do funcionário a ser criado e data de nascimento , Então por meio de uma estrutura condicional  é chamado um método que verifica se o usuário que está logado possui em sua lista  do tipo  “Permissões” o “Enum” referente a permissão de cadastro, caso não possua, é lançada uma exceção do tipo “ControllerException”. Caso ele possua a permissão, então é feito uma verificação para  garantir que o novo funcionário a ser criado não seja do tipo “Diretor” ou  um cargo inválido.Passado essas verificações , um novamente é delegado a um método da  classe “BancoFuncionários” para a criação do novo funcionário com nome, cargo e data de nascimento, caso algum parametro seja inválido, é lançada uma exceção do tipo “FuncionarioException “ou “DataInvalidaException”(ambas abaixo de ControllerException em hierarquia). Esse método retorna a matricula referente ao novo funcionário cadastrado que por sua vez, é retornada novamente pelo método de “cadastraFuncionários” do “Controller”. 
	Quando um funcionário é criado,  em seu construtor não abstrato, é chamado um método sobrescrito que define no seu atributo “listadepermissoes”, justificando o uso de herança nesse caso(Pois todos os funcionários compartilham de mesmos estados porem o comportamento desse método é distinto pra cada um deles).

CASO DE USO 2
	Para a atualização de informações de funcionários, criou-se um método que recebe a matrícula do usuário (cuja verificação será realizada), o atributo a ser alterado e o novo valor. O sistema então verificará se o atributo é válido (pela implementação de um switch)  e a partir disso, delegará para a classe Banco de Funcionários, que por sua vez realizará a alteração. Exceções serão lançadas caso a matrícula não corresponda à nenhum funcionário ou caso o atributo seja inválido. 
Para a alteração da senha, o sistema fará uma verificação da senha antiga e outra com a nova (se corresponde aos requisitos de tamanho e caracteres) e então, ocorrerá a alteração. Tal alteração poderá ser realizada pela matrícula (apenas se o usuário logado for o Diretor Geral) ou apenas para o usuário em si. Caso alguma verificação, seja da senha antiga ou da nova não passar, o sistema lançará exceção do tipo “FuncionarioException”. As verificações mais genéricas, como nome, senha, data, etc. são todas realizadas numa classe Util, cujos métodos são utilizados no Controller, antes da delegação para outras classes.

CASO DE USO 3
	
Cadastro de Pacientes:
	Para o cadastro de pacientes, foi elaborado um método que recebe como parâmetro as informações do paciente. Então, por meio de uma estrutura condicional, é chamado um método de verificação do Objeto Funcionário para verificar se ele possui ou não permissão para realizar tal função. Caso não possua, é lançada uma exceção do tipo “ControllerException”, caso possua, então são feitos os métodos de verificação dos parâmetros fornecidos, para evitar o uso de parâmetros inválidos. Caso algum parâmetro desse seja inválido, então serão lançadas exceções do tipo “PacienteException”  ou “DataInvalidaException” (ambos herdam de “ControllerException” na hierarquia). Passado os parâmetros, ocorre então uma delegação para um método da classe “BancoPacientes” (responsável por gerenciar manipular e armazenar tudo relacionado ao Objeto Pacientes). Esse método cria um novo objeto do tipo paciente armazena o mesmo na estrutura de dados e retorna uma string referente a id do paciente criado, que por sua vez será retornada também pelo método do “Controller”.

Criação  e ordenação de Prontuários:
	Ao ser criado um novo paciente, automaticamente é gerado um prontuario com as informações do mesmo. Isso ocorre pois dentro do método de construção do objeto paciente, é instanciado no atributo prontuario do tipo “Prontuario” um novo Objeto desse tipo, recebendo como parâmetro todas as informações referentes ao paciente criado. 
Como se faz necessária a ordenação dos prontuários em ordem alfabética, foi então implementado o método “compareTo” que compara os nomes entre dois prontuarios de pacientes distintos, assim sendo possível aplicar o método “Sort” pertencente ao “Collections”.

CASO DE USO 4	

	Foi criada uma classe Farmácia, que encapsularia os métodos e a coleção (ArrayList) de medicamentos. A factory de medicamentos também foi implementada para a criação de medicamentos. O design de herança foi utilizado para a criação dos Medicamentos. O Medicamento Genérico contém todos os métodos (com um getPreco que retorna o preço com o desconto de 40%) e o Medicamento de Referência herda de Genérico, fazendo uma sobrescrita nos métodos getTipo e getPreco (que retorna o preço integral). Foi criado um enum para as categorias, que será usado para a verificação de validade em métodos de acesso e alteração. Como as categorias vêm separadas por vírgula, criou-se um método que faria a separação e por fim, a adição das categorias à um atributo do tipo String (para facilitar tanto para verificar se o medicamento pertencia a uma categoria, usando o contains, quanto para o retorno, visto que já estaria no formato especificado). 
Para a busca de medicamentos por categoria, a Farmácia primeiramente verifica se a categoria é valida e a partir disso cria uma nova String adicionando os medicamentos continham cuja categoria, separando-os por vírgula. Para a busca por nome, o procedimento é semelhante. Ambos os métodos de busca, caso não encontrem um medicamento que não corresponda ao atributo pesquisado, lançam uma exceção do tipo “Medicamento Exception”. Em relação à ordenação, usou-se um construtor do Collections.sort que recebia um Comparator, possibilitando, assim, ordenações distintas.

CASO DE USO 5

	Foi criado um Banco de Órgãos, semelhante ao Banco de Funcionários e Medicamentos, com uma coleção (ArrayList) de objetos do tipo Órgão. Antes da criação do Órgão em sua factory correspondente, também é feita uma verificação de validade do tipo Sanguíneo, também usado em para pacientes (dessa forma, o método verificador se encontra na classe Util). O Banco de Órgãos contém métodos de busca por nome e tipo sanguíneo, que lançam exceções do tipo “OrgãoException”, caso o órgão buscado não esteja cadastrado. 

CASO DE USO 6

Realização de procedimentos:
Para suprir a especificação desse caso,  foram criados 3 métodos no Controller com assinaturas distintas (todos com mesmo nome, porém assinaturas diferentes): 

> Um para Procedimentos como Cirurgia Bariátrica e Redesignação Sexual (Procedimento, Id do paciente,  Medicamentos);
> Um para consultas clínicas (Procedimento, id do paciente);
> Um para transplante de órgãos (Procedimento, Id do paciente, órgão, Medicamentos).

	Embora distintos, seus comportamentos na classe “Controller” eram bem semelhantes, todos verificam se o usuário logado possui permissão ou não por meio de uma estrutura condicional que chama um método de verificação já mencionado em alguns casos anteriores. Caso ele possua permissão,  então são chamados os métodos de verificação de procedimento e id do paciente para evitar dados inválidos (essas verificações são feitas pela classe Util). No caso do procedimento transplante de órgãos, além desses métodos de verificação , também é verificado se o nome do órgão é válido.
Feitas as verificações, os procedimentos de consulta cirurgia bariátrica e redesignação sexual delegam a classe “BancoPacientes” a função de realizar o procedimento passando como parâmetros o procedimento, o id do paciente e o nome do medico que esta realizando o procedimento.
Esse método por sua vez analisa o tipo de procedimento e faz a instancia do Objeto do tipo “Iprocedimento” (Interface) de acordo com o tipo de procedimento (podendo ele ser do tipo das classes CirurgiaBariatrica, ConsultaClinica, Redesignação Sexual ou Transplante de Órgãos) que possuem o mesmo método sobrescrito com comportamentos diferentes (polimorfismo), tal estilo foi adotado por não haver a necessidade de um alto acoplamento e reuso dos estados de cada um, mas existir a necessidade de sobrescrita de um método.
	Enquanto o procedimento de transplante de órgãos irá verificar ainda se existe órgão disponível compatível com o tipo sanguíneo do paciente , por meio de uma estrutura condicional  que lança “OrgaoException” caso não possua órgãos compatíveis.

CASO DE USO 7

	Para o cartão, foi criada uma interface “CartaoFidelidade”, que contém os métodos:
	> Adicionar pontos;
	> Aplicar desconto;
	> GetPontosCartão; 

	E as classes Padrão, Master e Vip, ambas implementando a interface e sobrecarregando os métodos da mesma (possibilitando o polimorfismo). Na classe paciente foi adicionado um atributo do tipo CartaoFidelidade que seria associado ao tipo de fidelidade atual do paciente. Tal atributo foi implementado para que mude dinamicamente, aplicando o conceito de Strategy. Ao realizar quaisquer procedimentos, ocorre uma verificação que, caso necessário, altera o tipo de fidelidade. Assim, os métodos de pontos e de desconto se comportarão de acordo com a fidelidade do paciente.
	As chamadas polimórficas ocorrem quando o paciente chama algum método da classe instanciada no atributo CartaoFidelidade.

CASO DE USO 8

Para gravar os dados dos prontuários de forma persistente, foi criado um método “exportaFichaPaciente”  que recebe como parâmetro o Id do paciente e apenas delega a função à classe “BancoPaciente”. Esta, por sua vez, instancia o objeto referente ao Id fornecido e usa um método próprio do Objeto Paciente “registrarInformacoesEmArquivo” que define o caminho e o nome do arquivo a ser gerado e instancia uma “Stream” do tipo “FileWriter” usando caminho como parâmetro. Este então  é usado como parâmetro para instanciar o buffer , daí usa-se o método “buffer.write” usando o método sobrescrito “toString” como parâmetro.

CASO DE USO 9
	Para salvar o estado do sistema de forma persistente e recuperá-lo ao reutilizar o mesmo, foram gerados dois métodos:

	> InicializaSistema: na própria “Facade” que tenta ler os dados de um arquivo “SOOS.dat” por meio da stream “ObjectInputReader” e instancia como um objeto do tipo “Controller”.
	> FechaSistema: que verifica se não há mais ninguém logado e caso não tenha, escreve um arquivo do tipo “Controller” com o nome “SOOS.dat”.