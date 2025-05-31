const apiUrl = 'http://localhost:8080/api/pacientes';

// Registrar paciente
document.getElementById('registroPaciente').addEventListener('submit', async function(event) {
    event.preventDefault();

    const nombre = document.getElementById('nombre').value;
    const email = document.getElementById('email').value;
    const edad = document.getElementById('edad').value;

    const paciente = { nombre, email, edad };

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(paciente)
        });

        const data = await response.json();
        alert(data.message || "Paciente registrado con éxito");
        cargarPacientes();
    } catch (error) {
        alert("Error al registrar paciente");
    }
});

// Obtener lista de pacientes
async function cargarPacientes() {
    try {
        const response = await fetch(apiUrl);
        const pacientes = await response.json();
        const tabla = document.querySelector("#tablaPacientes tbody");
        tabla.innerHTML = "";

        pacientes.forEach(paciente => {
            const row = `<tr>
                <td>${paciente.id}</td>
                <td>${paciente.nombre}</td>
                <td>${paciente.email}</td>
                <td>${paciente.edad}</td>
                <td>
                    <button onclick="eliminarPaciente(${paciente.id})">Eliminar</button>
                    <button onclick="editarPaciente(${paciente.id}, '${paciente.nombre}', '${paciente.email}', ${paciente.edad})">Editar</button>
                </td>
            </tr>`;
            tabla.innerHTML += row;
        });
    } catch (error) {
        console.error("Error al cargar pacientes:", error);
    }
}

// Eliminar paciente
async function eliminarPaciente(id) {
    if (!confirm("¿Seguro que quieres eliminar este paciente?")) return;

    try {
        const response = await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });

        if (response.ok) {
            alert("Paciente eliminado");
            cargarPacientes();
        } else {
            alert("Error al eliminar paciente");
        }
    } catch (error) {
        console.error("Error al eliminar paciente:", error);
    }
}

// Editar paciente (actualizar datos)
async function editarPaciente(id, nombre, email, edad) {
    const nuevoNombre = prompt("Nuevo nombre:", nombre);
    const nuevoEmail = prompt("Nuevo email:", email);
    const nuevaEdad = prompt("Nueva edad:", edad);

    if (nuevoNombre && nuevoEmail && nuevaEdad) {
        const pacienteActualizado = { nombre: nuevoNombre, email: nuevoEmail, edad: nuevaEdad };

        try {
            const response = await fetch(`${apiUrl}/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(pacienteActualizado)
            });

            const data = await response.json();
            alert(data.message || "Paciente actualizado correctamente");
            cargarPacientes();
        } catch (error) {
            console.error("Error al actualizar paciente:", error);
        }
    }
}

// Cargar pacientes al iniciar la página
document.addEventListener("DOMContentLoaded", cargarPacientes);