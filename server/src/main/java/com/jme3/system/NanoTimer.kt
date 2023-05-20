/*
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jme3.system

/**
 * `NanoTimer` is a System.nanoTime implementation of
 * `Timer`. This is primarily useful for headless applications
 * running on a server.
 *
 * @author Matthew D. Hicks
 */
open class NanoTimer : Timer() {
    private var startTime = System.nanoTime()
    private var previousTime: Long = 0
    override var timePerFrame = 0f
    override var frameRate = 0f
    override var time: Long = 0
	override val resolution = Companion.RESOLUTION

    private val timeInternal: Long
        /**
         * Returns the time in seconds. The timer starts at 0.0 seconds.
         *
         * @return the current time in seconds
         */
		get() = System.nanoTime() - startTime

    override fun update() {
        time = timeInternal
        timePerFrame = (time - previousTime) * (1.0f / resolution)
        frameRate = 1.0f / timePerFrame
        previousTime = time
    }

    override fun reset() {
        startTime = System.nanoTime()
        time = timeInternal
        previousTime = time
    }

    companion object {
		private val RESOLUTION = 1000000000L
        private val INVERSE_TIMER_RESOLUTION = 1f / RESOLUTION
    }
}
