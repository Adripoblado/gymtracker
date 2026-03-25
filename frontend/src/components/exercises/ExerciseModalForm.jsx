import React, { useState } from 'react';
import api from '../../services/api';

const ExerciseModalForm = ({ exercise, onClose, onSuccess, catalogs }) => {
    const [formData, setFormData] = useState(() => {
        if (exercise) {
            return {
                id: exercise.id || 0,
                name: exercise.name || '',
                description: exercise.description || '',
                muscleGroupIds: exercise.muscleGroups?.map(m => m.id || m) ||[],
                exerciseTypeIds: exercise.exerciseTypes?.map(t => t.id || t) || [],
                equipmentIds: exercise.equipments?.map(e => e.id || e) ||[]
            };
        }
        return {
            name: '',
            description: '',
            muscleGroupIds: [],
            exerciseTypeIds: [],
            equipmentIds: []
        };
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    }

    const handleToggleSelection = (fieldName, id) => {
        setFormData(prevData => {
            const currentArray = prevData[fieldName];
            const newArray = currentArray.includes(id)
                ? currentArray.filter(item => item !== id)
                : [...currentArray, id];

                return { ...prevData, [fieldName]: newArray };
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (formData.muscleGroupIds.length === 0 || formData.exerciseTypeIds.length === 0 || formData.equipmentIds.lenght === 0) {
            alert("Please select at least one Muscle Group, Type and Equipment.")
            return;
        }

        const payload = {
            id: formData.id,
            name: formData.name,
            description: formData.description,
            muscleGroupIds: formData.muscleGroupIds.map(item => typeof item === 'object' ? item.id : item),
            exerciseTypeIds: formData.exerciseTypeIds.map(item => typeof item === 'object' ? item.id : item),
            equipmentIds: formData.equipmentIds.map(item => typeof item === 'object' ? item.id : item)
        }

        console.log("Sending Payload:", payload);

        try {
            if (exercise) {
                await api.put(`/exercises/modify/${exercise.id}`, payload);
            } else {
                console.log({ ...formData })
                await api.post('/exercises/create', payload);
            }
            onSuccess();
            onClose();
        } catch (error) {
            console.error(error);
            alert("Error while saving the exercise!");
        }
    };

    const renderPills = (title, fieldName, catalogArray) => (
        <div style={styles.section}>
            <label style={styles.label}>{title}</label>
            <div style={styles.pillsContainer}>
                {catalogArray?.map(item => {
                    const isSelected = formData[fieldName].includes(item.id);
                    return (
                        <button
                            key={item.id}
                            type="button" // MUY IMPORTANTE: Para que no envíe el formulario al hacer clic
                            onClick={() => handleToggleSelection(fieldName, item.id)}
                            style={{
                                ...styles.pill,
                                ...(isSelected ? styles.pillSelected : styles.pillUnselected)
                            }}
                        >
                            {item.name}
                        </button>
                    );
                })}
            </div>
        </div>
    );

    return (
        <div style={styles.overlay}>
            <div style={styles.modal}>
                <h2 style={{marginTop: 0, color: '#333'}}>{exercise ? 'Edit Exercise' : 'New Exercise'}</h2>
                
                <form onSubmit={handleSubmit} style={styles.form}>
                    
                    <input 
                        name="name" 
                        value={formData.name} 
                        onChange={handleChange}
                        placeholder="Exercise name" 
                        style={styles.input}
                        required 
                    />

                    <textarea 
                        name="description" 
                        value={formData.description} 
                        onChange={handleChange}
                        placeholder="Description" 
                        style={{...styles.input, minHeight: '60px'}}
                    />

                    {renderPills('Muscle Groups', 'muscleGroupIds', catalogs?.muscleGroups)}
                    {renderPills('Exercise Types', 'exerciseTypeIds', catalogs?.exerciseTypes)}
                    {renderPills('Equipment Required', 'equipmentIds', catalogs?.equipments)}

                    <div style={styles.actions}>
                        <button type="button" onClick={onClose} style={styles.cancelBtn}>Cancel</button>
                        <button type="submit" style={styles.saveBtn}>Save Exercise</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

const styles = {
    overlay: { position: 'fixed', top: 0, left: 0, right: 0, bottom: 0, backgroundColor: 'rgba(0,0,0,0.6)', display: 'flex', justifyContent: 'center', alignItems: 'center', zIndex: 1000 },
    modal: { backgroundColor: 'white', padding: '25px', borderRadius: '12px', width: '500px', maxHeight: '90vh', overflowY: 'auto', boxShadow: '0 20px 40px rgba(0,0,0,0.2)' },
    form: { display: 'flex', flexDirection: 'column', gap: '15px' },
    input: { padding: '12px', borderRadius: '8px', border: '1px solid #ddd', fontSize: '1rem', fontFamily: 'inherit', outline: 'none' },
    section: { display: 'flex', flexDirection: 'column', gap: '8px' },
    label: { fontSize: '0.9rem', fontWeight: 'bold', color: '#444' },
    
    pillsContainer: { display: 'flex', flexWrap: 'wrap', gap: '8px' },
    
    pill: { padding: '6px 14px', borderRadius: '20px', border: '1px solid #ccc', fontSize: '0.85rem', cursor: 'pointer', transition: 'all 0.2s ease', fontFamily: 'inherit', fontWeight: '500' },
    
    pillUnselected: { backgroundColor: '#f8f9fa', color: '#555', borderColor: '#ddd' },
    pillSelected: { backgroundColor: '#28a745', color: 'white', borderColor: '#28a745', boxShadow: '0 2px 5px rgba(40, 167, 69, 0.3)' },
    
    actions: { display: 'flex', justifyContent: 'flex-end', gap: '12px', marginTop: '20px', borderTop: '1px solid #eee', paddingTop: '20px' },
    cancelBtn: { padding: '10px 20px', cursor: 'pointer', border: 'none', background: '#e9ecef', color: '#495057', borderRadius: '8px', fontWeight: 'bold', transition: '0.2s' },
    saveBtn: { padding: '10px 20px', cursor: 'pointer', border: 'none', background: '#28a745', color: 'white', borderRadius: '8px', fontWeight: 'bold', transition: '0.2s' }
};

export default ExerciseModalForm;