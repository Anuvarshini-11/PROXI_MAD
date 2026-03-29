const express = require('express');
const { getAllUsers, getAllSessions, deleteSession, deactivateUser } = require('../controllers/adminController');
const { protect, admin } = require('../middleware/authMiddleware');
const router = express.Router();

router.get('/users', protect, admin, getAllUsers);
router.get('/sessions', protect, admin, getAllSessions);
router.delete('/sessions/:id', protect, admin, deleteSession);
router.delete('/users/:id', protect, admin, deactivateUser);

module.exports = router;
