import React from 'react';
import api from '../../services/api';

const ExerciseCard = ({ exercise, onEdit, refreshData }) => {
    const currentUsername = localStorage.getItem('username');

    const handleDelete = async () => {
        if (window.confirm('Are you sure you want to delete "${exercise.name}"?')) {
            try {
                await api.delete('/exercises/${exercise.id}');
                refreshData();
            } catch (err) {
                alert("You don't have permission to delete this exercise.")
            }
        }
    };

    const getTagArray = (data) => {
        if (!data) return[];
        if (Array.isArray(data)) return data;
        if (typeof data === 'string') return data.split(',').map(item => item.trim());
        return [];
    };

    return (
        <div style={styles.card}>
            <div style={styles.header}>
                <h3 style={styles.title}>{exercise.name}</h3>
                {exercise.isCustom && <span style={styles.customBadge}>Custom</span>}
            </div>
            
            <p style={styles.description}>{exercise.description || 'No description.'}</p>
            
            <div style={styles.tags}>
                {getTagArray(exercise.muscleGroup).map((muscle) => (
                <span key={`${exercise.id}-muscle-${muscle}`} style={styles.tagMuscle}>
                    {muscle.trim()}
                </span>
                ))}

                {getTagArray(exercise.exerciseType).map((t) => (
                    <span key={`${exercise.id}-type-${t}`} style={styles.tagType}>
                        {t.trim()}
                    </span>
                ))}

                {getTagArray(exercise.equipment).map((item) => (
                    <span key={`${exercise.id}-equip-${item}`} style={styles.tagEquipment}>
                        {item.trim()}
                    </span>
                ))}
            </div>

            {exercise.isCustom && exercise.owner === currentUsername && (
                <div style={styles.actions}>
                    <button onClick={() => onEdit(exercise)} style={styles.editBtn}>Edit</button>
                    <button onClick={handleDelete} style={styles.deleteBtn}>Delete</button>
                </div>
            )}
        </div>
    );
};

const styles = {
    card: { backgroundColor: 'white', borderRadius: '10px', padding: '15px', boxShadow: '0 4px 6px rgba(0,0,0,0.1)', border: '1px solid #eee', display: 'flex', flexDirection: 'column', height: 'auto', minHeight: '220px', width: '100%', boxSizing: 'border-box', justifyContent: 'space-between' },
    header: { display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start', marginBottom: '10px' },
    title: { margin: 0, fontSize: '1.2rem', color: '#333' },
    customBadge: { fontSize: '0.7rem', backgroundColor: '#fff3cd', color: '#856404', padding: '2px 6px', borderRadius: '4px', border: '1px solid #ffeeba' },
    description: { fontSize: '0.9rem', color: '#666', marginBottom: '15px', display: '-webkit-box', WebkitLineClamp: 3, WebkitBoxOrient: 'vertical', overflow: 'hidden' },
    tags: { display: 'flex', flexWrap: 'wrap', flexDirection: 'row', gap: '8px', marginTop: '10px', marginBottom: '15px', overflow: 'visible', width: '100%', alignContent: 'flex-start' },
    tag: { fontSize: '0.75rem', backgroundColor: '#f0f0f0', padding: '4px 8px', borderRadius: '12px', fontWeight: 'bold' },
    actions: { display: 'flex', gap: '10px', borderTop: '1px solid #eee', paddingTop: '10px' },
    editBtn: { flex: 1, padding: '6px', cursor: 'pointer', backgroundColor: '#ffc107', border: 'none', borderRadius: '4px' },
    deleteBtn: { flex: 1, padding: '6px', cursor: 'pointer', backgroundColor: '#dc3545', color: 'white', border: 'none', borderRadius: '4px' },
    tagBase: { display: 'inline-block', whiteSpace: 'nowrap', fontSize: '0.7rem', padding: '4px 8px', borderRadius: '12px', fontWeight: 'bold', textTransform: 'uppercase'},
    tagMuscle: { backgroundColor: '#e8f5e9', color: '#2e7d32', fontSize: '0.7rem', padding: '4px 8px', borderRadius: '12px', fontWeight: 'bold' },
    tagType: { backgroundColor: '#e3f2fd', color: '#1976d2', fontSize: '0.7rem', padding: '4px 8px', borderRadius: '12px', fontWeight: 'bold' },
    tagEquipment: { backgroundColor: '#f5f5f5', color: '#616161', fontSize: '0.7rem', padding: '4px 8px', borderRadius: '12px', fontWeight: 'bold', border: '1px solid #e0e0e0' }
};

export default ExerciseCard;