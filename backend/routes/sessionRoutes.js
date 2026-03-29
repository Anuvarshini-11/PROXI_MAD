const express = require('express');
const { createSession, getNearbySessions, joinSession, getSessionDetails } = require('../controllers/sessionController');
const { protect } = require('../middleware/authMiddleware');
const router = express.Router();

router.post('/', protect, createSession);
router.get('/nearby', protect, getNearbySessions);
router.post('/:id/join', protect, joinSession);
router.get('/:id', protect, getSessionDetails);

module.exports = router;
