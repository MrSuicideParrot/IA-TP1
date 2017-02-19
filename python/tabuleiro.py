from queue import Queue
import copy

class Tabuleiro:
    """criar tabuleiro com alteração de posições"""
    lado = 4
    def changeNumber(self, tabu, zero, target):
        self.jogo = copy.deepcopy(tabu.jogo)
        self.last = zero
        x0, y0 = zero
        xt, yt = target
        xt += x0
        yt += y0
        # if self.isValid(xt, yt): # poupar tempo
        self.jogo[x0][y0] = copy.deepcopy(tabu.jogo[xt][yt])
        self.jogo[xt][yt] = 0

    def findZero(self):
        for i in range(self.lado):
            for j in range(self.lado):
                if self.jogo[i][j] == 0:
                    return i, j
        return None

    def isValid(self, x, y):
        return 0 <= x < self.lado and 0 <= y < self.lado

    def distMin(self, x0, y0, target):
        moveX = [0, 1, 0, -1]
        moveY = [-1, 0, 1, 0]
        visited = [[False for _ in range(self.lado)] for _ in range(self.lado)]
        fila = Queue()
        fila.put((x0, y0, 0))
        visited[x0][y0] = True
        while not fila.empty():
            x, y, index = fila.get()
            if self.jogo[x][y] == target:
                return index
            for i in range(4):
                if self.isValid(x + moveX[i], y + moveY[i]) and not visited[x + moveX[i]][y + moveY[i]]:
                    fila.put((x + moveX[i], y + moveY[i], index + 1))
        print('Erro, problema com distMin!')
        return None

    def moves(self):
        moveX = [0, 1, 0, -1]
        moveY = [-1, 0, 1, 0]
        x0, y0 =self.zero
        return [Tabuleiro(self, self.zero, (x, y)) for x, y in zip(moveX, moveY) if (x0+x,y0+y) != self.last and self.isValid(x0+x, y0+y)]

    def __eq__(self, other):
        for i in range(self.lado):
            for j in range(self.lado):
                if not self.jogo[i][j] == other.jogo[i][j]:
                    return False
        return True


    """Calculo da distância final"""
    def dist(self, target):
        aux = 0
        for i in range(self.lado):
            for j in range(self.lado):
                aux += target.distMin(i, j, self.jogo[i][j])
                # print(aux)
        return aux

    """ Retorna tuplo para ser usado na heap"""
    def tabuleiroPrioritario(self, target):
        return self.dist(target), self

    """ criar tabuleiro com alteração de posições """
    def __init__(self, tabu, zero=None, target=None):
        if target is None:
            self.jogo = list(tabu)
            self.last = None
        else:
            self.changeNumber(tabu, zero, target)
        self.zero = self.findZero()

    def printTab(self):
        print(self.jogo)


