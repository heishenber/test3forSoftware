document.addEventListener('DOMContentLoaded', loadContacts);

function loadContacts() {
    fetch('/api/contacts')
        .then(response => response.json())
        .then(contacts => {
            const contactList = document.getElementById('contacts');
            contactList.innerHTML = '';
            contacts.forEach(contact => {
                const li = document.createElement('li');
                li.textContent = `姓名: ${contact.name}, 地址: ${contact.address}, 电话: ${contact.phone}`;
                const deleteButton = document.createElement('button');
                deleteButton.textContent = '删除';
                deleteButton.onclick = () => deleteContact(contact.name);
                li.appendChild(deleteButton);
                contactList.appendChild(li);
            });
        });
}

function addContact() {
    const name = document.getElementById('name').value;
    const address = document.getElementById('address').value;
    const phone = document.getElementById('phone').value;

    fetch('/api/contacts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, address, phone })
    }).then(() => {
        loadContacts();
    });
}

function deleteContact(name) {
    fetch(`/api/contacts/${name}`, { method: 'DELETE' })
        .then(() => {
            loadContacts();
        });
}
