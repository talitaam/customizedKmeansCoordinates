
# Descrição:

Algoritmo Kmeans em Java que clusteriza coordenadas bidimensionais (eixo X e Y). 

Trabalho prático para a disciplina Planejamento de Capacidade e Avaliação de Sistemas Computacionais do Bacharelado em Engenharia de Software da Pontifícia Universidade Católica de Minas Gerais - PUC Minas.

# Composição:

- ## Alunos:
  - Ariel Santos Barcelos
  - Ezequiel de Carvalho Santos
  - Karolina Vaz Coelho
  - Talita Arantes Melo

- ## Professores:
  - Claudio Correa

# Funcionamento:

Entrada: 
- Quantidade de Clusters: variável clusterQty;
- Definição dos primeiros centróides de cada cluster: variável randomlySelectFirstCentroids
  - por sorteio -> randomlySelectFirstCentroids = true;
  - por ordem de inserção das coordenadas -> randomlySelectFirstCentroids = false;
- Quantidade máxima de interações: variável maxRounds;
- Lista de coordenadas: variável data;

Execução:
- Enquanto alguma coordenada trocar de cluster ou o número máximo de iterações não for atingido:
  - calcula a distância entre cada coordenada e o centróide de cada cluster; 
  - inclui a coordenada no cluster cujo centróide estiver mais próximo;
  - recalcula o centróide de cada cluster: para cada coordenada de um centróide, é feita a média das correspondentes coordenadas de todos os elementos do cluster.

Saída:
- Cálculos e mudanças realizadas durante a execução do algoritmo
- Motivo da parada do algoritmo: Número máximo de interações foi atindo ou Não nenhuma coordenada mudou mais de cluster
  - "------->>> MAXIMUM NUMBER OF ROUNDS REACHED <<<-------"
  - "------->>> THERE WAS NO CHANGE OF CLUSTER. NO MORE ROUNDS NEEDED <<<-------"
- Total de interações
  - "Total rounds:"
- Informação final de cada coordenada: ID, eixo X, eixo Y e o número do cluster a que pertence
  - "Coordinate [id=, X=, Y=, cluster=]"
- Informação final de cada cluster: valor final dos centróides e o id das coordenadas que pertencem a ele
  - "Key = Cluster [Xc=, Yc=, cluster=], Value = [Coordinate [id=], Coordinate [id=], Coordinate [id=]]"


