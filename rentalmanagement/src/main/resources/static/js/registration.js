document.getElementById('registerForm').addEventListener('submit',
  function(event) {
    event.preventDefault();  // Prevent the form from submitting normally

    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const contact = document.getElementById('contact').value;

    // Create the AJAX request
    fetch('/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: `firstName=${firstName}&lastName=${lastName}&contact=${contact}`,
    })
    .then(response => response.text())  // Read the response as text
    .then(message => {
      // Display the success message in the modal
      document.getElementById('responseMessage').innerText = message;
      alert(message);  // Show a success alert
    })
    .catch(error => console.error('Error:', error));
  });