// Отримуємо кнопку
const scrollToTopBtn = document.getElementById('scrollToTopBtn');

// Показуємо кнопку при досягненні кінця сторінки
window.addEventListener('scroll', () => {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
        scrollToTopBtn.style.display = 'block';
    } else {
        scrollToTopBtn.style.display = 'none';
    }
});

// Прокрутка до верху при натисканні кнопки
scrollToTopBtn.addEventListener('click', () => {
    window.scrollTo({
        top: 0,
        behavior: 'smooth' // Плавна прокрутка
    });
});
