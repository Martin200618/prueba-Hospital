const apiUrlAsignacion = 'http://localhost:8080/api/paciente-medicamento';
const apiUrlPacientes = 'http://localhost:8080/api/pacientes';
const apiUrlMedicamentos = 'http://localhost:8080/api/medicamentos';

// Cargar opciones de pacientes y medicamentos en los selects
async function cargarOpciones() {
    const pacientes = await fetch(apiUrlPacientes).then(res => res.json());
    const medicamentos = await fetch(apiUrlMedicamentos).then(res => res.json());

    const selectPacientes = document.getElementById("pacienteId");
    const selectMedicamentos = document.getElementById("medicamentoId");

    selectPacientes.innerHTML = pacientes.map(p => `<option value="${p.id}">${p.nombre}</option>`).join('');
    selectMedicamentos.innerHTML = medicamentos.map(m => `<option value="${m.id}">${m.nombre}</option>`).join('');
}

// Registrar asignación
document.getElementById('registroAsignacion').addEventListener('submit', async function(event) {
    event.preventDefault();

    const pacienteId = document.getElementById('pacienteId').value;
    const medicamentoId = document.getElementById('medicamentoId').value;
    const frecuencia = document.getElementById('frecuencia').value;

    const asignacion = { pacienteId, medicamentoId, frecuencia };

    try {
        const response = await fetch(apiUrlAsignacion, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(asignacion)
        });

        const data = await response.json();
        alert(data.message || "Asignación registrada con éxito");
        cargarAsignaciones();
    } catch (error) {
        alert("Error al registrar asignación");
    }
});

// Obtener lista de asignaciones
async function cargarAsignaciones() {
    try {
        const response = await fetch(apiUrlAsignacion);
        const asignaciones = await response.json();
        const tabla = document.querySelector("#tablaAsignaciones tbody");
        tabla.innerHTML = "";

        asignaciones.forEach(asignacion => {
            const row = `<tr>
                <td>${asignacion.id}</td>
                <td>${asignacion.paciente.nombre}</td>
                <td>${asignacion.medicamento.nombre}</td>
                <td>${asignacion.frecuencia}</td>
                <td>
                    <button onclick="eliminarAsignacion(${asignacion.id})">Eliminar</button>
                    <button onclick="editarAsignacion(${asignacion.id}, '${asignacion.frecuencia}')">Editar</button>
                </td>
            </tr>`;
            tabla.innerHTML += row;
        });
    } catch (error) {
        console.error("Error al cargar asignaciones:", error);
    }
}

// Eliminar asignación
async function eliminarAsignacion(id) {
    if (!confirm("¿Seguro que quieres eliminar esta asignación?")) return;

    try {
        const response = await fetch(`${apiUrlAsignacion}/${id}`, { method: 'DELETE' });

        if (response.ok) {
            alert("Asignación eliminada");
            cargarAsignaciones();
        } else {
            alert("Error al eliminar asignación");
        }
    } catch (error) {
        console.error("Error al eliminar asignación:", error);
    }
}

// Editar asignación
async function editarAsignacion(id, frecuencia) {
    const nuevaFrecuencia = prompt("Nueva frecuencia:", frecuencia);

    if (nuevaFrecuencia) {
        const asignacionActualizada = { frecuencia: nuevaFrecuencia };

        try {
            const response = await fetch(`${apiUrlAsignacion}/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(asignacionActualizada)
            });

            const data = await response.json();
            alert(data.message || "Asignación actualizada correctamente");
            cargarAsignaciones();
        } catch (error) {
            console.error("Error al actualizar asignación:", error);
        }
    }
}

// Cargar opciones y asignaciones al iniciar la página
document.addEventListener("DOMContentLoaded", () => {
    cargarOpciones();
    cargarAsignaciones();
});