// Supponiamo di avere due contesti di canvas con le immagini
let ctx1, ctx2;

// Ottieni i dati dell'immagine
let imgData1 = ctx1.getImageData(0, 0, ctx1.canvas.width, ctx1.canvas.height);
let imgData2 = ctx2.getImageData(0, 0, ctx2.canvas.width, ctx2.canvas.height);

// Trova le differenze
for (let i = 0; i < imgData1.data.length; i += 4) {
    if (imgData1.data[i] !== imgData2.data[i] ||
        imgData1.data[i + 1] !== imgData2.data[i + 1] ||
        imgData1.data[i + 2] !== imgData2.data[i + 2]) {
        // Imposta il pixel a rosso per evidenziare la differenza
        imgData1.data[i] = 255;     // Rosso
        imgData1.data[i + 1] = 0;   // Verde
        imgData1.data[i + 2] = 0;   // Blu
    }
}

// Disegna le differenze sul primo canvas
ctx1.putImageData(imgData1, 0, 0);
