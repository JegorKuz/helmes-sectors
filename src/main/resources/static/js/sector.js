document.addEventListener('DOMContentLoaded', () => {

    const saveButton = document.getElementById('saveButton');
    const nameInput = document.getElementById('name');
    const sectorsSelect = document.getElementById('sectors');
    const termsCheckbox = document.getElementById('terms');

    loadFormData();

    nameInput.addEventListener('input', saveFormData);
    sectorsSelect.addEventListener('change', saveFormData);
    termsCheckbox.addEventListener('change', saveFormData);

    saveButton.addEventListener('click', handleSave);
});

function getFormData() {
    const nameInput = document.getElementById('name');
    const sectorsSelect = document.getElementById('sectors');
    const termsCheckbox = document.getElementById('terms');

    const name = nameInput.value;
    const selectedSector = sectorsSelect.value;
    const termsAgreed = termsCheckbox.checked;

    return {
        name: name,
        sectorId: selectedSector,
        terms: termsAgreed
    };
}

function saveFormData() {
    const formData = getFormData();
    localStorage.setItem('sectorFormData', JSON.stringify(formData));
    console.log('Form data saved to local storage.');
}

function loadFormData() {
    const savedData = localStorage.getItem('sectorFormData');

    if (savedData) {
        const formData = JSON.parse(savedData);
        const nameInput = document.getElementById('name');
        const sectorsSelect = document.getElementById('sectors');
        const termsCheckbox = document.getElementById('terms');

        nameInput.value = formData.name || '';
        termsCheckbox.checked = formData.terms || false;

        // Restore selected options in the multiselect
        if (formData.sectors) {
            formData.sectors.forEach(sectorId => {
                const option = sectorsSelect.querySelector(`option[value="${sectorId}"]`);
                if (option) {
                    option.selected = true;
                }
            });
        }

        console.log('Form data loaded from local storage.');
    }
}

function validateForm(formData) {
    if (!formData.name.trim()) {
        alert('User must insert name');
        return false;
    }

    if (!formData.sectorId) {
        alert('User must select sector');
        return false;
    }

    if (!formData.terms) {
        alert('User must agree to terms');
        return false;
    }

    return true;
}

function handleSave() {
    const formData = getFormData();

    if (!validateForm(formData)) {
        return;
    }

    const csrfToken = document.querySelector('input[name="_csrf"]').value;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

    fetch('/api/v1/user/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(formData),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to save data. Please try again.');
            }
            alert('Data saved successfully!');
        })
        .catch(error => {
            alert(error);
        });
}