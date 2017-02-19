class Node:
    def __init__(self,altura,tabu,heur):
        self.altura = altura
        self.tabu = tabu
        if heur:
           heuristica = None

