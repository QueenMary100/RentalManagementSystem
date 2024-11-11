const searchInput = document.querySelector('.search-bar input');
const apartmentList = document.querySelector('.apartment-list');

searchInput.addEventListener('input', (event) => {
    const searchTerm = event.target.value.toLowerCase();
    const apartments = apartmentList.querySelectorAll('.apartment');

    apartments.forEach((apartment) => {
        const apartmentName = apartment.querySelector('.details h4').textContent.toLowerCase();
        if (apartmentName.includes(searchTerm)) {
            apartment.style.display = 'block';
        } else {
            apartment.style.display = 'none';
        }
    });
});