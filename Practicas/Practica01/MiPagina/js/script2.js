// Cambiar color de subtítulos al pasar el mouse
const subtitulos = document.querySelectorAll(".subtitulo");

subtitulos.forEach(sub => {
  sub.addEventListener("mouseover", () => {
    sub.style.color = "orange";
  });
  sub.addEventListener("mouseout", () => {
    sub.style.color = "darkgreen";
  });
});
