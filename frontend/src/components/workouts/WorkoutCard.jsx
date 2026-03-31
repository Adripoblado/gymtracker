import React, { memo } from 'react';
import api from '../../services/api';

const WorkoutCard = memo(({ workout, onEdit, refreshData }) => {
    const currentUserId = localStorage.getItem('user_id');
    const isAdmin = localStorage.getItem('role') === 'ADMIN';

    const canManage = isAdmin || (Number(workout.userId) === Number(currentUserId));

    const handleDelete = async () => {
        if (window.confirm(`Are you sure you want to delete ${workout.name}?`)) {
            try {
                await api.delete(`/workouts/delete/${workout.id}`);
                refreshData();
            } catch (err) {
                alert("You don't have permission to delete this workout.")
            }
        }
    };

    const formatDate = (dateString) => {
        if (!dateString) return 'No date';
        const date = new Date(dateString);
        return date.toLocaleDateString('es-ES', {
            day: '2-digit',
            month: 'short',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
    };

    const getAllMuscleGroups = () => {
        const muscleGroups = [];
        const seenIds = new Set();

        workout.exercises?.forEach(we => {
            console.log("Processing exercise:", workout);
            const groups = Array.isArray(we.muscleGroup) ? we.muscleGroup : [we.muscleGroup];

            groups.forEach(mg => {
                if (mg && mg.id && !seenIds.has(mg.id)) {
                    seenIds.add(mg.id);
                    muscleGroups.push(mg);
                }
            });
        });
        return muscleGroups;
    };

    return (
        <div style={styles.card}>
            <div style={styles.header}>
                <div>
                    <h3 style={styles.title}>{workout.name || 'Untitled Workout'}</h3>
                    <p style={styles.dateSubtitle}>
                        {formatDate(workout.startTime)} 
                        {workout.endTime && ` - ${formatDate(workout.endTime).split(',')[1]}`}
                    </p>
                </div>
            </div>

            <div style={styles.exerciseListContainer}>
                <ul style={styles.exerciseList}>
                    {workout.exercises?.map((we, index) => (
                        <li key={we.id || index} style={styles.exerciseItem}>
                            <span style={styles.exerciseName}>{we?.exerciseName}</span>
                            <span style={styles.setsBadge}>{we.sets?.length || 0} sets</span>
                        </li>
                    ))}
                </ul>
            </div>
            
            <div style={styles.tags}>
                {getAllMuscleGroups().map((muscle) => (
                    <span key={muscle.id} style={styles.tagMuscle}>
                        {muscle.name}
                    </span>
                ))}
            </div>

            <div style={styles.actions}>
                <button onClick={() => onEdit(workout)} style={styles.editBtn}>Edit</button>
                <button onClick={() => onDelete(workout.id)} style={styles.deleteBtn}>Delete</button>
            </div>
        </div>
    );
});

const styles = {
    card: { backgroundColor: 'white', borderRadius: '10px', padding: '15px', boxShadow: '0 4px 6px rgba(0,0,0,0.1)', border: '1px solid #eee', display: 'flex', flexDirection: 'column', height: 'auto', minHeight: '250px', width: '100%', boxSizing: 'border-box', justifyContent: 'space-between' },
    header: { display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start', marginBottom: '12px' },
    title: { margin: 0, fontSize: '1.2rem', color: '#333',fontWeight: 'bold' },
    dateSubtitle: { margin: '4px 0 0 0', fontSize: '0.75rem', color: '#888', },
    exerciseListContainer: { flex: 1, marginBottom: '15px' },
    exerciseList: { listStyle: 'none', padding: 0, margin: 0, display: 'flex', flexDirection: 'column', gap: '6px' },
    exerciseItem: { display: 'flex', justifyContent: 'space-between', alignItems: 'center', fontSize: '0.85rem', color: '#555', padding: '4px 0', borderBottom: '1px dashed #f0f0f0' },
    exerciseName: { fontWeight: '500' },
    setsBadge: {fontSize: '0.7rem',backgroundColor: '#f0f0f0',padding: '2px 6px',borderRadius: '4px',color: '#666' },
    tags: { display: 'flex', flexWrap: 'wrap', gap: '6px', marginBottom: '15px' },
    tagMuscle: { backgroundColor: '#e8f5e9', color: '#2e7d32', fontSize: '0.65rem', padding: '3px 8px', borderRadius: '10px', fontWeight: 'bold',textTransform: 'uppercase' },
    actions: { display: 'flex', gap: '10px', borderTop: '1px solid #eee', paddingTop: '10px' },
    editBtn: { flex: 1, padding: '8px', cursor: 'pointer', backgroundColor: '#ffc107', border: 'none', borderRadius: '6px',fontWeight: 'bold' },
    deleteBtn: { flex: 1, padding: '8px', cursor: 'pointer', backgroundColor: '#dc3545', color: 'white', border: 'none', borderRadius: '6px',fontWeight: 'bold' }
};

export default WorkoutCard;