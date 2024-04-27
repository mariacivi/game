// JavaScript per il gioco Snake

// Definizione costanti
const canvas = document.getElementById('gameCanvas');
const ctx = canvas.getContext('2d');
const blockSize = 10;
const canvasWidth = canvas.width;
const canvasHeight = canvas.height;

// Inizializzazione Snake
let snake = [{x: 10, y: 10}];
let dx = blockSize;
let dy = 0;

// Inizializzazione cibo
let food = {x: Math.floor(Math.random() * (canvasWidth / blockSize)) * blockSize,
            y: Math.floor(Math.random() * (canvasHeight / blockSize)) * blockSize};

// Funzione di disegno
function draw() {
  ctx.clearRect(0, 0, canvasWidth, canvasHeight);
  drawSnake();
  drawFood();
  moveSnake();
  checkCollision();
}

// Disegna il serpente
function drawSnake() {
  ctx.fillStyle = 'green';
  snake.forEach(segment => {
    ctx.fillRect(segment.x, segment.y, blockSize, blockSize);
  });
}

// Disegna il cibo
function drawFood() {
  ctx.fillStyle = 'red';
  ctx.fillRect(food.x, food.y, blockSize, blockSize);
}

// Muove il serpente
function moveSnake() {
  const head = {x: snake[0].x + dx, y: snake[0].y + dy};
  snake.unshift(head);
  if (head.x === food.x && head.y === food.y) {
    generateFood();
  } else {
    snake.pop();
  }
}

// Genera nuovo cibo
function generateFood() {
  food = {x: Math.floor(Math.random() * (canvasWidth / blockSize)) * blockSize,
          y: Math.floor(Math.random() * (canvasHeight / blockSize)) * blockSize};
}

// Verifica collisione
function checkCollision() {
  if (snake[0].x < 0 || snake[0].x >= canvasWidth || snake[0].y < 0 || snake[0].y >= canvasHeight) {
    alert('Game over!');
    resetGame();
  }
  for (let i = 1; i < snake.length; i++) {
    if (snake[i].x === snake[0].x && snake[i].y === snake[0].y) {
      alert('Game over!');
      resetGame();
    }
  }
}

// Resetta il gioco
function resetGame() {
  snake = [{x: 10, y: 10}];
  dx = blockSize;
  dy = 0;
  generateFood();
}

// Gestore degli eventi per il movimento
document.addEventListener('keydown', function(event) {
  const keyPressed = event.key;
  if (keyPressed === 'ArrowLeft' && dx !== blockSize) {
    dx = -blockSize;
    dy = 0;
  }
  if (keyPressed === 'ArrowRight' && dx !== -blockSize) {
    dx = blockSize;
    dy = 0;
  }
  if (keyPressed === 'ArrowUp' && dy !== blockSize) {
    dx = 0;
    dy = -blockSize;
  }
  if (keyPressed === 'ArrowDown' && dy !== -blockSize) {
    dx = 0;
    dy = blockSize;
  }
});

// Loop di gioco
setInterval(draw, 100);
