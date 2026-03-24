import React from 'react';
import api from '../../services/api';

const ExerciseModalForm = ({ exercise, onClose, onSuccess }) => {
    const [formData, setFormData] = useState(
        exercise || { name: '', muscleGroup: 'CHEST', type: 'STRENGTH', description: '' }
    );

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (exercise) {
                await api.put('/exercises/${exercise.id}', formData);
            } else {
                await api.post('/exercises', { ...formData, isCustom: true });
            }
            onSuccess();
            onClose();
        } catch (error) {
            alert("Error while saving the exercise!");
        }
    };

    return (
        <div style={styles.modalOverlay}>
            <div style={styles.modalContent}>
                <h2>{exercise ? 'Editar Ejercicio' : 'Nuevo Ejercicio'}</h2>
                <form onSubmit={handleSubmit}>
                    <input 
                        name="name" 
                        value={formData.name} 
                        onChange={(e) => setFormData({...formData, name: e.target.value})}
                        placeholder="Nombre del ejercicio" 
                        required 
                    />
                    {/* ... más inputs para muscleGroup y type ... */}
                    <div style={styles.modalActions}>
                        <button type="button" onClick={onClose}>Cancelar</button>
                        <button type="submit" style={styles.saveBtn}>Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

    const styles = {
    overlay: { position: 'fixed', top: 0, left: 0, right: 0, bottom: 0, backgroundColor: 'rgba(0,0,0,0.5)', display: 'flex', justifyContent: 'center', alignItems: 'center', zIndex: 1000 },
    modal: { backgroundColor: 'white', padding: '30px', borderRadius: '8px', width: '400px', boxShadow: '0 10px 25px rgba(0,0,0,0.2)' },
    form: { display: 'flex', flexDirection: 'column', gap: '15px' },
    input: { padding: '10px', borderRadius: '4px', border: '1px solid #ccc', fontSize: '1rem' },
    actions: { display: 'flex', justifyContent: 'flex-end', gap: '10px', marginTop: '10px' },
    cancelBtn: { padding: '10px 20px', cursor: 'pointer', border: 'none', background: '#ccc', borderRadius: '4px' },
    saveBtn: { padding: '10px 20px', cursor: 'pointer', border: 'none', background: '#28a745', color: 'white', borderRadius: '4px' }
};

export default ExerciseModalForm;