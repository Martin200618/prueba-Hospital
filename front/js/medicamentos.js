const apiUrl = 'http://localhost:8080/api/medicamentos';

// Registrar medicamento
document.getElementById('registroMedicamento').addEventListener('submit', async function(event) {
    event.preventDefault();

    const nombre = document.getElementById('nombre').value;
    const descripcion = document.getElementById('descripcion').value;
    const dosis = document.getElementById('dosis').value;

    const medicamento = { nombre, descripcion, dosis };

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(medicamento)
        });

        const data = await response.json();
        alert(data.message || "Medicamento registrado con éxito");
        cargarMedicamentos();
    } catch (error) {
        alert("Error al registrar medicamento");
    }
});

// Obtener lista de medicamentos
async function cargarMedicamentos() {
    try {
        const response = await fetch(apiUrl);
        const medicamentos = await response.json();
        const tabla = document.querySelector("#tablaMedicamentos tbody");
        tabla.innerHTML = "";

        medicamentos.forEach(medicamento => {
            const row = `<tr>
                <td>${medicamento.id}</td>
                <td>${medicamento.nombre}</td>
                <td>${medicamento.descripcion}</td>
                <td>${medicamento.dosis}</td>
                <td>
                    <button onclick="eliminarMedicamento(${medicamento.id})">Eliminar</button>
                    <button onclick="editarMedicamento(${medicamento.id}, '${medicamento.nombre}', '${medicamento.descripcion}', '${medicamento.dosis}')">Editar</button>
                </td>
            </tr>`;
            tabla.innerHTML += row;
        });
    } catch (error) {
        console.error("Error al cargar medicamentos:", error);
    }
}

// Eliminar medicamento
async function eliminarMedicamento(id) {
    if (!confirm("¿Seguro que quieres eliminar este medicamento?")) return;

    try {
        const response = await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });

        if (response.ok) {
            alert("Medicamento eliminado");
            cargarMedicamentos();
        } else {
            alert("Error al eliminar medicamento");
        }
    } catch (error) {
        console.error("Error al eliminar medicamento:", error);
    }
}

// Editar medicamento
async function editarMedicamento(id, nombre, descripcion, dosis) {
    const nuevoNombre = prompt("Nuevo nombre:", nombre);
    const nuevaDescripcion = prompt("Nueva descripción:", descripcion);
    const nuevaDosis = prompt("Nueva dosis:", dosis);

    if (nuevoNombre && nuevaDescripcion && nuevaDosis) {
        const medicamentoActualizado = { nombre: nuevoNombre, descripcion: nuevaDescripcion, dosis: nuevaDosis };

        try {
            const response = await fetch(`${apiUrl}/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(medicamentoActualizado)
            });

            const data = await response.json();
            alert(data.message || "Medicamento actualizado correctamente");
            cargarMedicamentos();
        } catch (error) {
            console.error("Error al actualizar medicamento:", error);
        }
    }
}

// Cargar medicamentos al iniciar la página
document.addEventListener("DOMContentLoaded", cargarMedicamentos);