import React, { useState, useEffect } from 'react';
import api from '../services/api';
import ExerciseFilters from '../components/exercises/ExerciseFilters';
import ExerciseList from '../components/exercises/ExerciseList';
import ExerciseModalForm from '../components/exercises/ExerciseModalForm';

const ExercisesPage = () => {
    const [exercises, setExercises] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [filters, setFilters] = useState({ muscleGroup: '', type: ''});

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedExercise, setSelectedExercise] = useState(null);

    const fetchExercises = async () => {
        setIsLoading(true);
        try {
            const response = await api.get('/exercises/list', { params: filters });
            setExercises(response.data);
        } catch (err) {
            console.error("Error while loading exercises: ", error);
        } finally {
            setIsLoading(false);
        }
    };

    useEffect(() => {
        fetchExercises();
    }, [filters]);

    const handleOpenModal = (exercise = null) => {
        setSelectedExercise(exercise);
        setIsModalOpen(true);
    };

    return (
        <div style={styles.pageContainer}>
            <header style={styles.header}>
                <h1>Exercise Index</h1>
                <button style={styles.addBtn} onClick={() => handleOpenModal()}>
                    + New Exercise
                </button>
            </header>

            <ExerciseFilters filters={filters} setFilters={setFilters} />

            {isLoading ? (
                <div style={styles.spinner}>Loading exercises...</div>
            ) : (
                <ExerciseList 
                    exercises={exercises} 
                    onEdit={handleOpenModal} 
                    refreshData={fetchExercises}
                />
            )}

            {isModalOpen && (
                <ExerciseModalForm 
                    exercise={selectedExercise} 
                    onClose={() => setIsModalOpen(false)} 
                    onSuccess={fetchExercises}
                />
            )}
        </div>
    );
};

const styles = {
    pageContainer: { padding: '20px' },
    header: { display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '20px' },
    addBtn: { padding: '10px 20px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' },
    spinner: { textAlign: 'center', marginTop: '50px', fontSize: '1.2rem', color: '#666' }
};

export default ExercisesPage;