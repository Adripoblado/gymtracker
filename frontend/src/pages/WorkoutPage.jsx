import React, { useEffect, useState } from 'react';
import api from '../services/api';
import WorkoutFilters from '../components/workouts/WorkoutFilters';
import WorkoutList from '../components/workouts/WorkoutList';
import WorkoutModalForm from '../components/workouts/WorkoutModalForm';
import { useCatalogs } from '../hooks/useCatalogs';

const WorkoutPage = () => {
    const [workouts, setWorkouts] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    const [filters, setFilters] = useState({
        startDate: '',
        endDate: '',
        muscleGroupId: ''
    });

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedWorkout, setSelectedWorkout] = useState(null);

    const { muscleGroups, exerciseTypes, equipments } = useCatalogs();
    const catalogs = { muscleGroups: muscleGroups || [], exerciseTypes: exerciseTypes || [], equipments: equipments || [] };


    const fetchWorkouts = async () => {
        setIsLoading(true);
        try {
            const response = await api.get('/workouts/get', { params: filters });
            setWorkouts(response.data);
        } catch (error) {
            console.error("Error loading workouts:", error);
        } finally {
            setIsLoading(false);
        }
    };

    useEffect(() => {
        fetchWorkouts();
    }, []);

    const handleOpenModal = (workout = null) => {
        setSelectedWorkout(workout);
        setIsModalOpen(true);
    };

    const handleStartLiveWorkout = () => {
        alert("This feature is not available yet.");
    }

    return (
        <div style={styles.pageContainer}>
            <header style={styles.header}>
                <h1>My Workouts</h1>
                <div style={styles.headerButtons}>
                    <button style={styles.addBtn} onClick={() => handleOpenModal()}>
                        + Log Workout
                    </button>
                    <button style={styles.liveBtn} onClick={handleStartLiveWorkout}>
                        ▶ Start Live Workout
                    </button>
                </div>
            </header>

            <WorkoutFilters filters={filters} setFilters={setFilters} catalogs={catalogs} />

            {isLoading ? (
                <div style={styles.spinner}>Loading workouts...</div>
            ) : (
                <WorkoutList 
                    workouts={workouts} 
                    onEdit={handleOpenModal} 
                    refreshData={fetchWorkouts}
                />
            )}

            {isModalOpen && (
                <WorkoutModalForm 
                    workout={selectedWorkout} 
                    onClose={() => setIsModalOpen(false)} 
                    onSuccess={fetchWorkouts}
                    catalogs={catalogs}
                />
            )}
        </div>
    );
};

const styles = {
    pageContainer: { padding: '20px', maxWidth: '1200px', margin: '0 auto' },
    header: { display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '20px', flexWrap: 'wrap', gap: '15px' },
    headerButtons: { display: 'flex', gap: '10px' },
    addBtn: { padding: '10px 20px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '8px', cursor: 'pointer', fontWeight: 'bold' },
    liveBtn: { padding: '10px 20px', backgroundColor: '#dc3545', color: 'white', border: 'none', borderRadius: '8px', cursor: 'pointer', fontWeight: 'bold', display: 'flex', alignItems: 'center', gap: '5px' },
    spinner: { textAlign: 'center', marginTop: '50px', fontSize: '1.2rem', color: '#666' }
};

export default WorkoutPage;