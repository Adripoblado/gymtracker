import React, { useState, useEffect, use } from 'react';
import api from '../../services/api';
import { useNavigate } from 'react-router-dom';

const WorkoutModalForm = ({ workout, onClose, onSuccess, catalogs }) => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState(() => {
        if (workout) {
            return {
                id: workout.id,
                name: workout.name || '',
                startTime: workout.startTime || new Date().toISOString().slice(0, 16),
                endTime: workout.endTime || '',
                exercises: workout.exercises || []
            };
        }

        return {
            name: '',
            startTime: new Date().toISOString().slice(0, 16),
            endTime: '',
            exercises: []
        };
    });

    const [searchTerm, setSearchTerm] = useState('');
    const [exerciseFilters, setExerciseFilters] = useState({ muscleGroupId: '', exerciseTypeId: '', equipmentId: '' });
    const [availableExercises, setAvailableExercises] = useState([]);

    useEffect(() => {
        const fetchExercises = async () => {
            try {
                const params = {
                    name: searchTerm,
                    muscleId: exerciseFilters.muscleGroupId,
                    typeId: exerciseFilters.exerciseTypeId,
                    equipmentId: exerciseFilters.equipmentId
                };
                const response = await api.get('/exercises/get', { params });
                setAvailableExercises(response.data);
            } catch (error) {
                console.error('Error fetching exercises:', error);
            }
        };
        fetchExercises();
    }, [searchTerm, exerciseFilters]);


    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const addExerciseToWorkout = (exercise) => {
        const newExercise = {
            exerciseId: exercise.id,
            name: exercise.name,
            orderIndex: formData.exercises.length + 1,
            sets: [{ setNumber: 1, reps: 0, weight: 0 }]
        };
        setFormData({ ...formData, exercises: [...formData.exercises, newExercise] });
    };

    const removeExercise = (index) => {
        const updated = formData.exercises.filter((_, i) => i !== index).map((ex, i) => ({ ...ex, orderIndex: i + 1 }));;
        setFormData({ ...formData, exercises: updated });
    };

    const addSet = (exerciseIndex) => {
        const updatedExercises = [...formData.exercises];
        const sets = updatedExercises[exerciseIndex].sets;
        sets.push({ setNumber: sets.length + 1, reps: 0, weight: 0 });
        setFormData({ ...formData, exercises: updatedExercises });
    };

    const updateSet = (exerciseIndex, setIndex, field, value) => {
        const updatedExercises = [...formData.exercises];
        updatedExercises[exerciseIndex].sets[setIndex][field] = parseFloat(value) || 0;
        setFormData({ ...formData, exercises: updatedExercises });
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (formData.exercises.length === 0) {
            alert("Please add at least one exercise to the workout.");
            return;
        }

        try {
            const response = workout
                ? await api.put(`/workouts/modify/${workout.id}`, formData)
                : await api.post('/workouts/create', formData);

            console.log("Response data:", response.data);
            onSuccess();
            onClose();
        } catch (error) {
            if (error.response?.status === 401) {
                localStorage.clear();
                navigate('/login');
            } else {
                alert("An error occurred while saving the workout. Please try again.");
                console.error("Error saving workout:", error);
            }
        }
    };

    return (
        <div style={styles.overlay}>
            <div style={styles.modal}>
                <h2 style={{ marginTop: 0 }}>{workout ? 'Edit Workout' : 'Log Workout'}</h2>
                
                <form onSubmit={handleSubmit} style={styles.form}>
                    <div style={styles.row}>
                        <input name="name" value={formData.name} onChange={handleChange} placeholder="Workout Name (e.g. Leg Day)" style={styles.input} required />
                    </div>
                    
                    <div style={styles.row}>
                        <div style={styles.section}>
                            <label style={styles.label}>Start Time</label>
                            <input type="datetime-local" name="startTime" value={formData.startTime} onChange={handleChange} style={styles.input} required />
                        </div>
                        <div style={styles.section}>
                            <label style={styles.label}>End Time (Optional)</label>
                            <input type="datetime-local" name="endTime" value={formData.endTime} onChange={handleChange} style={styles.input} />
                        </div>
                    </div>

                    <hr style={styles.hr} />

                    <div style={styles.searchSection}>
                        <h4 style={{margin: '0 0 10px 0'}}>Add Exercises</h4>
                        <input 
                            type="text" 
                            placeholder="Search exercise..." 
                            value={searchTerm} 
                            onChange={(e) => setSearchTerm(e.target.value)} 
                            style={styles.input} 
                        />
                        <div style={styles.exercisePool}>
                            {availableExercises.slice(0, 6).map(ex => (
                                <button key={ex.id} type="button" onClick={() => addExerciseToWorkout(ex)} style={styles.addExBtn}>
                                    + {ex.name}
                                </button>
                            ))}
                        </div>
                    </div>

                    <div style={styles.selectedExercises}>
                        {formData.exercises.map((ex, exIdx) => (
                            <div key={exIdx} style={styles.exerciseCard}>
                                <div style={styles.exerciseHeader}>
                                    <strong>{ex.orderIndex}. {ex.name || `Exercise ID: ${ex.exerciseId}`}</strong>
                                    <button type="button" onClick={() => removeExercise(exIdx)} style={styles.removeBtn}>Remove</button>
                                </div>
                                
                                <div style={styles.setsHeader}>
                                    <span>Set</span><span>Reps</span><span>Weight (kg)</span>
                                </div>
                                {ex.sets.map((set, sIdx) => (
                                    <div key={sIdx} style={styles.setRow}>
                                        <span style={styles.setNum}>{set.setNumber}</span>
                                        <input type="number" value={set.reps} onChange={(e) => updateSet(exIdx, sIdx, 'reps', e.target.value)} style={styles.setInput} />
                                        <input type="number" step="0.5" value={set.weight} onChange={(e) => updateSet(exIdx, sIdx, 'weight', e.target.value)} style={styles.setInput} />
                                    </div>
                                ))}
                                <button type="button" onClick={() => addSet(exIdx)} style={styles.addSetBtn}>+ Add Set</button>
                            </div>
                        ))}
                    </div>

                    <div style={styles.actions}>
                        <button type="button" onClick={onClose} style={styles.cancelBtn}>Cancel</button>
                        <button type="submit" style={styles.saveBtn}>Save Workout</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

const styles = {
    overlay: { position: 'fixed', top: 0, left: 0, right: 0, bottom: 0, backgroundColor: 'rgba(0,0,0,0.6)', display: 'flex', justifyContent: 'center', alignItems: 'center', zIndex: 1000 },
    modal: { backgroundColor: 'white', padding: '25px', borderRadius: '12px', width: '600px', maxHeight: '90vh', overflowY: 'auto', boxShadow: '0 20px 40px rgba(0,0,0,0.2)' },
    form: { display: 'flex', flexDirection: 'column', gap: '15px' },
    row: { display: 'flex', gap: '15px', width: '100%' },
    input: { padding: '10px', borderRadius: '8px', border: '1px solid #ddd', width: '100%', boxSizing: 'border-box' },
    label: { fontSize: '0.8rem', color: '#666', marginBottom: '4px' },
    section: { flex: 1, display: 'flex', flexDirection: 'column' },
    hr: { border: '0', borderTop: '1px solid #eee', margin: '10px 0' },
    
    searchSection: { background: '#f8f9fa', padding: '15px', borderRadius: '8px' },
    exercisePool: { display: 'flex', flexWrap: 'wrap', gap: '8px', marginTop: '10px' },
    addExBtn: { padding: '5px 10px', background: 'white', border: '1px solid #28a745', color: '#28a745', borderRadius: '15px', cursor: 'pointer', fontSize: '0.8rem' },
    
    selectedExercises: { display: 'flex', flexDirection: 'column', gap: '15px' },
    exerciseCard: { border: '1px solid #eee', borderRadius: '8px', padding: '12px', background: '#fff' },
    exerciseHeader: { display: 'flex', justifyContent: 'space-between', marginBottom: '10px', alignItems: 'center' },
    removeBtn: { background: 'none', border: 'none', color: '#dc3545', cursor: 'pointer', fontSize: '0.8rem' },
    
    setsHeader: { display: 'grid', gridTemplateColumns: '40px 1fr 1fr', gap: '10px', fontSize: '0.75rem', color: '#888', marginBottom: '5px', textAlign: 'center' },
    setRow: { display: 'grid', gridTemplateColumns: '40px 1fr 1fr', gap: '10px', marginBottom: '5px' },
    setNum: { display: 'flex', alignItems: 'center', justifyContent: 'center', fontWeight: 'bold' },
    setInput: { padding: '6px', borderRadius: '4px', border: '1px solid #ddd', textAlign: 'center' },
    addSetBtn: { marginTop: '8px', background: 'none', border: '1px dashed #aaa', padding: '5px', borderRadius: '4px', cursor: 'pointer', width: '100%', fontSize: '0.8rem', color: '#666' },

    actions: { display: 'flex', justifyContent: 'flex-end', gap: '12px', marginTop: '20px', paddingTop: '15px', borderTop: '1px solid #eee' },
    cancelBtn: { padding: '10px 20px', background: '#e9ecef', border: 'none', borderRadius: '8px', cursor: 'pointer', fontWeight: 'bold' },
    saveBtn: { padding: '10px 20px', background: '#28a745', color: 'white', border: 'none', borderRadius: '8px', cursor: 'pointer', fontWeight: 'bold' }
};

export default WorkoutModalForm;