import React, { useState, useEffect } from 'react';
import api from '../services/api';
import ExerciseFilters from '../components/exercises/ExerciseFilters';
import ExerciseList from '../components/exercises/ExerciseList';
import ExerciseModalForm from '../components/exercises/ExerciseModalForm';

const ExercisesPage = () => {
    const [exercises, setExercises] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [filters, setFilters] = useState({ muscleGroupId: '', exerciseTypeId: '', equipmentId: '', onlyMine: false });

    const [catalogs, setCatalogs] = useState({
        muscleGroups:[],
        exerciseTypes:[],
        equipments:[]
    });

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedExercise, setSelectedExercise] = useState(null);

    const filteredExercises = React.useMemo(() => {
        const currentUserId = localStorage.getItem('user_id');
        const isAdmin = localStorage.getItem('role') === 'ADMIN';

        return exercises.filter(exercise => {
            if (!filters.onlyMine) return true;
            return isAdmin || (Number(exercise.userId) === Number(currentUserId));
        });
    }, [exercises, filters.onlyMine]);

    const fetchCatalogs = async () => {
        try {
            const [resMuscle, resType, resEquip] = await Promise.all([
                api.get('/api/catalogs/muscle-groups'),
                api.get('/api/catalogs/exercise-types'),
                api.get('/api/catalogs/equipments')
            ]);
            
            setCatalogs({
                muscleGroups: resMuscle.data,
                exerciseTypes: resType.data,
                equipments: resEquip.data
            });
        } catch (err) {
            console.error("Error loading catalogs: ", err);
        }
    };

    const fetchExercises = async () => {
        setIsLoading(true);
        try {
            const response = await api.get('/exercises/get', { params: filters });
            setExercises(response.data);
        } catch (err) {
            console.error("Error while loading exercises: ", error);
        } finally {
            setIsLoading(false);
        }
    };

    useEffect(() => {
        fetchCatalogs();
    },[]);

    useEffect(() => {
        fetchExercises();
    }, [filters]);

    const handleOpenModal = React.useCallback((exercise = null) => {
        setSelectedExercise(exercise);
        setIsModalOpen(true);
    }, []);

    return (
        <div style={styles.pageContainer}>
            <header style={styles.header}>
                <h1>Exercise Index</h1>
                <button style={styles.addBtn} onClick={() => handleOpenModal()}>
                    + New Exercise
                </button>
            </header>

            <ExerciseFilters filters={filters} setFilters={setFilters} catalogs={catalogs} />

            {isLoading ? (
                <div style={styles.spinner}>Loading exercises...</div>
            ) : (
                <ExerciseList 
                    exercises={filteredExercises} 
                    onEdit={handleOpenModal} 
                    refreshData={fetchExercises}
                />
            )}

            {isModalOpen && (
                <ExerciseModalForm 
                    exercise={selectedExercise} 
                    onClose={() => setIsModalOpen(false)} 
                    onSuccess={fetchExercises}
                    catalogs={catalogs}
                />
            )}
        </div>
    );
};

const styles = {
    pageContainer: { padding: '20px' },
    header: { display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '20px' },
    addBtn: { padding: '10px 20px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' },
    spinner: { textAlign: 'center', marginTop: '50px', fontSize: '1.2rem', color: '#666' },
    filterMineLabel: { display: 'flex', alignItems: 'center', gap: '8px', fontSize: '0.9rem', cursor: 'pointer', backgroundColor: '#f8f9fa', padding: '5px 10px', borderRadius: '5px', border: '1px solid #ddd' }
};

export default ExercisesPage;